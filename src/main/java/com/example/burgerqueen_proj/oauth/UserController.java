package com.example.burgerqueen_proj.oauth;

import com.example.burgerqueen_proj.member.entity.Member;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @GetMapping("/test")
    @ResponseBody
    public String formLoginInfo(Authentication authentication, @AuthenticationPrincipal PrincipalDetails principalDetails ){

        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        Member member = principal.getMember();
        System.out.println(member);

        Member member1 = principalDetails.getMember();
        System.out.println(member1);
        return  member.toString();
    }
}
