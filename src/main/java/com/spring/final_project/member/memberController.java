package com.spring.final_project.member;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class memberController {

    @GetMapping("sign-up")
    public String signUp(){
        return "member/signup";
    }

    @GetMapping("login")
    public String login() {
        return "member/login";
    }

    @GetMapping("mypage")
    public String mypage(String member, Model model) {
//        Member member로 정보 받아서 ㄱㄱ
        return "member/mypage";
    }

}
