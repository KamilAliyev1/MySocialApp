package com.kmsocialapp.myutil;

import com.kmsocialapp.security.usersecuritydetail.UserSecurityDetail;
import com.kmsocialapp.security.usersecuritydetail.UserSecurityDetailService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.List;

import static com.kmsocialapp.security.AppUserRoles.ADMIN;

public interface CustomService<T> {

    T findById(Long id) ;

    List findAll();

    void save(T obj);

    boolean existsById(Long id) ;

    void deleteById(Long id);

    void checkPermisson(Long id);


}
