package com.spring.final_project.main;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.swing.plaf.PanelUI;

@Controller
public class mainController {

    @GetMapping("/")
    public String root(Model model) {

        return "main";
    }

    @GetMapping("/search")
    public String search(String search_data, Model model){

        return "common/search";
    }

}
