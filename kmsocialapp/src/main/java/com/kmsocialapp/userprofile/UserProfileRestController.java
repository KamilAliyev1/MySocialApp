package com.kmsocialapp.userprofile;

import com.kmsocialapp.myutil.CustomRestController;
import com.kmsocialapp.myutil.CustomService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/userprofiles")
public class UserProfileRestController extends CustomRestController<UserProfile> {

    public UserProfileRestController(UserProfileService userProfileService) {
        super(userProfileService);
    }
    //@GetMapping("/post")

}
