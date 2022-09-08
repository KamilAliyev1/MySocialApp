package com.kmsocialapp.myutil;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public abstract class CustomService<T> {

    protected JpaRepository jpaRepository;

    public CustomService(JpaRepository jpaRepository) {
        this.jpaRepository=jpaRepository;
    }

    public T findById(Long id) {
        var temp = jpaRepository.findById(id);
        if(temp.isEmpty())throw new ResourcesNotFounded("not founded");
        return (T) temp.get();
    }

    public List findAll() {
        return jpaRepository.findAll();
    }

    public abstract T changeForRest(T t) ;

    public abstract void save(T obj);

    public boolean existsById(Long id) {
        return jpaRepository.existsById(id);
    }

    public void deleteById(Long id) {
        if(jpaRepository.existsById(id)) {
            jpaRepository.deleteById(id);
            return;
        }
        throw new ResourcesNotFounded("securitydetail not founded");
    }
}
