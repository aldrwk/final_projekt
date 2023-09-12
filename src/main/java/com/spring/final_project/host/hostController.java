package com.spring.final_project.host;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/host")
public class hostController {

    @GetMapping("/regist")
    public String regist(){
        return "host/regist";
    }

    @PostMapping("/registproc")
    public String registproc() {
        return "/";
    }
}
