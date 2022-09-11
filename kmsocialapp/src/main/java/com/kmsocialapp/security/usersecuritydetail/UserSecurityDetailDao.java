package com.kmsocialapp.security.usersecuritydetail;

import com.kmsocialapp.security.usersecuritydetail.UserSecurityDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserSecurityDetailDao extends JpaRepository<UserSecurityDetail,Long> {

    Optional<UserSecurityDetail> findByEmail(String email);

    Optional<UserSecurityDetail> findByPhonenumber(String phonenumber);

}