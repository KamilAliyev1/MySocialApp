package com.kmsocialapp.like;

import com.kmsocialapp.myutil.CustomService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class LikeService extends CustomService<Like> {
    public LikeService(LikeDao jpaRepository) {
        super(jpaRepository);
    }

    @Override
    public Like changeForRest(Like like) {
        like.setComment(null);
        like.setPost(null);
        like.setUserProfile(null);
        return null;
    }

    @Override
    public void save(Like obj) {
        jpaRepository.save(obj);
    }
}
