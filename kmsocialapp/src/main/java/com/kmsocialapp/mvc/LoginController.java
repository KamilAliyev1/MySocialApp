package com.kmsocialapp.mvc;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    private AuthenticationManager authenticationManager;

    @GetMapping
    public String getLogin(){
        return "login_page";
    }

}
