package com.kmsocialapp.post;

import com.kmsocialapp.myutil.CustomService;
import com.kmsocialapp.myutil.ResourcesNotFounded;
import org.springframework.stereotype.Service;

@Service
public class PostService extends CustomService<Post> {
    public PostService(PostDao postDao) {
        super(postDao);
    }

    @Override
    public Post changeForRest(Post post) {
        post.setComments(null);
        post.setLikes(null);
        post.setUserProfile(null);
        return post;
    }

    @Override
    public void save(Post post) {
        jpaRepository.save(post);
    }
}
