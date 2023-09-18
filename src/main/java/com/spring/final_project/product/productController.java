package com.spring.final_project.product;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller

public class productController {

    @GetMapping("products/{productNum}")
    public String productList(@PathVariable("productNum") int productNum, Model model) {

        return "product/detail";
    }
}
