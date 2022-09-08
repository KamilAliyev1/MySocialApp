package com.kmsocialapp.security;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserSecurityDetailDao extends JpaRepository<UserSecurityDetail,Long> {

    Optional<UserSecurityDetail> findByEmail(String email);

    Optional<UserSecurityDetail> findByPhonenumber(String phonenumber);

}