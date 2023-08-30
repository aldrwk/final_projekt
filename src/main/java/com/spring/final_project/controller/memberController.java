package com.spring.final_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class memberController {

    @GetMapping("/sign-up")
    public String signUp(){
        return "member/signup";
    }

    @GetMapping("login")
    public String login() {
        return "member/login";
    }
}
