package com.kmsocialapp.userprofile;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileDao extends JpaRepository<UserProfile,Long> {
}
