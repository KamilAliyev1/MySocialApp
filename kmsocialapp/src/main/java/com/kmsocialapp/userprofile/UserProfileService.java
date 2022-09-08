package com.kmsocialapp.userprofile;

import com.kmsocialapp.myutil.CustomService;
import com.kmsocialapp.post.PostService;
import org.springframework.stereotype.Service;


@Service
public class UserProfileService extends CustomService<UserProfile> {

    private final PostService postService;

    public UserProfileService(UserProfileDao userProfileDao, PostService postService) {
        super(userProfileDao);
        this.postService = postService;
    }

    @Override
    public UserProfile changeForRest(UserProfile userProfile) {
        userProfile.setPosts(null);
        userProfile.setChats(null);
        userProfile.setComments(null);
        userProfile.setLikes(null);
        return userProfile;
    }

    @Override
    public void save(UserProfile userProfile) {
        jpaRepository.save(userProfile);
    }
}
