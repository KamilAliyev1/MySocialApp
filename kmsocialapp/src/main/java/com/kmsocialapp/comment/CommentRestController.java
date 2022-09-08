package com.kmsocialapp.comment;

import com.kmsocialapp.myutil.CustomRestController;
import com.kmsocialapp.myutil.CustomService;
import org.springframework.data.jpa.repository.JpaRepository;

public class CommentRestController extends CustomRestController<Comment> {
    public CommentRestController(CommentService service) {
        super(service);
    }
}
