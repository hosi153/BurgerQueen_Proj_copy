//package com.example.burgerqueen_proj.member.controller;
//
//import com.example.burgerqueen_proj.member.entity.Member;
//import com.example.burgerqueen_proj.member.repository.MemberRepository;
//import com.example.burgerqueen_proj.member.service.MemberService;
//import com.example.burgerqueen_proj.oauth.PrincipalDetails;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import java.util.Map;
//
//@Controller
//public class MemberOAuthController {
//    private MemberRepository memberRepository;
//    private MemberService memberService;
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    public MemberOAuthController(MemberRepository memberRepository, MemberService memberService, BCryptPasswordEncoder bCryptPasswordEncoder) {
//        this.memberRepository = memberRepository;
//        this.memberService = memberService;
//        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
//    }
//
//    // !!!! OAuth로 로그인 시 이 방식대로 하면 CastException 발생함
//    @GetMapping("/form/loginInfo")
//    @ResponseBody
//    public String formLoginInfo(Authentication authentication, @AuthenticationPrincipal PrincipalDetails principalDetails){
//
//        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
//        Member member = principal.getMember();
//        System.out.println(member);
//        //User(id=2, username=11, password=$2a$10$m/1Alpm180jjsBpYReeml.AzvGlx/Djg4Z9/JDZYz8TJF1qUKd1fW, email=11@11, role=ROLE_USER, createTime=2022-01-30 19:07:43.213, provider=null, providerId=null)
//
//        Member member1 = principalDetails.getMember();
//        System.out.println(member1);
//        //User(id=2, username=11, password=$2a$10$m/1Alpm180jjsBpYReeml.AzvGlx/Djg4Z9/JDZYz8TJF1qUKd1fW, email=11@11, role=ROLE_USER, createTime=2022-01-30 19:07:43.213, provider=null, providerId=null)
//        //user == user1
//
//        return member.toString();
//    }
//
//
//    @GetMapping("/oauth/loginInfo")
//    @ResponseBody
//    public String oauthLoginInfo(Authentication authentication, @AuthenticationPrincipal OAuth2User oAuth2UserPrincipal){
//        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
//        Map<String, Object> attributes = oAuth2User.getAttributes();
//        System.out.println(attributes);
//        // PrincipalOauth2UserService의 getAttributes내용과 같음
//
//        Map<String, Object> attributes1 = oAuth2UserPrincipal.getAttributes();
//        // attributes == attributes1
//
//        return attributes.toString();     //세션에 담긴 user가져올 수 있음음
//    }
//
//
//    @GetMapping("/loginInfo")
//    @ResponseBody
//    public String loginInfo(Authentication authentication, @AuthenticationPrincipal PrincipalDetails principalDetails){
//        String result = "";
//
//        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
//        if(principal.getMember().getProvider() == null) {
//            result = result + "Form 로그인 : " + principal;
//        }else{
//            result = result + "OAuth2 로그인 : " + principal;
//        }
//        return result;
//    }
//
//
//
//}
