package com.kmsocialapp.security.usersecuritydetail;

import com.kmsocialapp.myutil.CustomRestController;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/usersecuritydetails")
public class UserSecurityDetailRestController extends CustomRestController<UserSecurityDetail> {

    public UserSecurityDetailRestController(UserSecurityDetailService userSecurityDetailService) {
        super(userSecurityDetailService);
    }


    //@GetMapping
    //public String refreshToken(HttpServletRequest request, HttpServletResponse response){
    //
//
    //}

}
