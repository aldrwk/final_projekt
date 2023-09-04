package com.spring.final_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/host")
public class hostController {

    @GetMapping("/regist")
    public String regist(){
        return "host/regist";
    }
}
