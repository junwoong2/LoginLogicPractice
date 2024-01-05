package com.study.LoginLogicPractice.Controller;

import com.study.LoginLogicPractice.Dto.MemberDto;
import com.study.LoginLogicPractice.Service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {
    // 생성자 주입
    private final MemberService memberService;

    //회원가입 페이지 요청
    @GetMapping("/member/save")
    public String saveForm(){
        return "save";
    }
    @PostMapping("/member/save")
    public String save(@ModelAttribute MemberDto memberDto){
        System.out.println("memberDto = " + memberDto);
        System.out.println("MemberController.save");
        memberService.save(memberDto);
        return "login";
    }

    @GetMapping("/member/login")
    public String loginForm(){
        return "login";
    }
    @PostMapping("/member/login") // 세션관리도 추가
    public String login(@ModelAttribute MemberDto memberDto, HttpSession session){
        MemberDto loginResult = memberService.login(memberDto); // 회원정보가 맞는지 확인
        if(loginResult != null){
            session.setAttribute("loginEmail", loginResult.getMemberEmail());
            return "main";
        }
        else{
            return "login";
        }
    }
}
