package com.kmsocialapp.like;


import com.kmsocialapp.myutil.CustomRestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/likes")
public class LikeRestController extends CustomRestController<Like> {
    public LikeRestController(LikeService service) {
        super(service);
    }
}
