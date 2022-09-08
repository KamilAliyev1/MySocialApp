package com.kmsocialapp.post;

import com.kmsocialapp.myutil.CustomRestController;
import com.kmsocialapp.myutil.CustomService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/posts")
public class PostRestController extends CustomRestController<Post> {
    public PostRestController(PostService service) {
        super(service);
    }
}
