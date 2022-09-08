package com.kmsocialapp.like;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeDao extends JpaRepository<Like,Long> {
}
