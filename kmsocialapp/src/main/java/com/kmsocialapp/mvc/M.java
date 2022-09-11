package com.kmsocialapp.mvc;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/profile")
public class M {



    @GetMapping
    @ResponseBody
    public String i(){
        return "asasa";
    }

}
