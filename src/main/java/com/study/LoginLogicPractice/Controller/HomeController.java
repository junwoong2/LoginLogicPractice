package com.study.LoginLogicPractice.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    // 기본 페이지 요청 메서드
    @GetMapping("/")
    public String index(){
        return "index"; // => templates 폴더의 index.html 로 들어감
    }
}
