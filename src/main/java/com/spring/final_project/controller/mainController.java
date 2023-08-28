package com.spring.final_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.swing.plaf.PanelUI;

@Controller
public class mainController {

    @GetMapping("/")
    public String root(){
        return "main";
    }
}
