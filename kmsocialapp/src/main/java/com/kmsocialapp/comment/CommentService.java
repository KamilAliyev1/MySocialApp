package com.kmsocialapp.comment;

import com.kmsocialapp.myutil.CustomService;
import com.kmsocialapp.myutil.ResourcesNotFounded;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService extends CustomService<Comment> {
    public CommentService(CommentDao jpaRepository) {
        super(jpaRepository);
    }

    @Override
    public Comment changeForRest(Comment comment) {
        comment.setChildComments(null);
        comment.setParentComment(null);
        comment.setPost(null);
        comment.setLikes(null);
        comment.setUserProfile(null);
        return null;
    }

    @Override
    public void save(Comment obj) {
        jpaRepository.save(obj);
    }
}
