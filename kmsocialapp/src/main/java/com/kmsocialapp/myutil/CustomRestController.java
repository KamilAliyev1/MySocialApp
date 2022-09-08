package com.kmsocialapp.myutil;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

public abstract class CustomRestController<T extends Resource> {

    protected final CustomService service;

    public CustomRestController(CustomService service) {
        this.service = service;
    }


    @GetMapping
    public ResponseEntity getAll(){
        List<T> objectList;
        try {
            objectList = service.findAll();

            objectList=objectList.stream().map(
                    obj -> (T)service.changeForRest(obj)
            ).collect(Collectors.toList());
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(objectList,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity add(@Valid @RequestBody T obj, Errors errors){
        obj.setId(null);
        if(errors.hasErrors()){
            return CustomErrorHandler.erorHandle(errors);
        }
        try{
            service.save(obj);
            obj= (T) service.changeForRest(obj);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(obj,HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity get(@PathVariable("id") Integer id){
        T obj;
        try{
            obj = (T) service.findById(id.longValue());
        }catch (ResourcesNotFounded e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        obj= (T) service.changeForRest(obj);

        return new ResponseEntity(obj,HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity change(@PathVariable("id") Integer id,@Valid @RequestBody T obj,Errors errors){
        if(errors.hasErrors()){
            return CustomErrorHandler.erorHandle(errors);
        }
        try {
            if(!service.existsById(Long.valueOf(id.longValue()))) throw new ResourcesNotFounded();
            service.save(obj);
        }catch (ResourcesNotFounded e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(obj,HttpStatus.CREATED);

    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteUserSecurityDetail(@PathVariable("id") Integer id){

        try {
            service.deleteById(id.longValue());
        } catch (ResourcesNotFounded e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);


    }



}
