package com.kmsocialapp.myutil;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;

import java.util.stream.Collectors;

public class CustomErrorHandler {


    public static ResponseEntity<String> erorHandle(Errors errors){
        if(!errors.hasFieldErrors())return new ResponseEntity(errors.getAllErrors().stream().map(
                e->e.getDefaultMessage()
        ).collect(Collectors.toList()), HttpStatus.BAD_REQUEST);
        return new ResponseEntity(errors.getFieldErrors().stream().map(
                e-> e.getField()+":"+e.getDefaultMessage()
        ).collect(Collectors.toList()), HttpStatus.BAD_REQUEST);
    }

}