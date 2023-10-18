//package com.example.burgerqueen_proj.oauth;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//@Controller
//public class LoginController {
//
//    @GetMapping("/test/login")
//    public @ResponseBody String testLogin(Authentication authentication, @AuthenticationPrincipal UserDetails userDetails){
//        System.out.println("/test/login ====================");
////        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
//        System.out.println("authentication : "+((PrincipalDetails) authentication.getPrincipal()).getUsername());//getPrincipal은 리턴타입이 오브젝트이다.
//        System.out.println("userDetails : " + userDetails.getUsername());
//        return "세션 정보 확인하기";
//    }
//
//    // 시큐리티 설정
//    @GetMapping("/user")
//    public @ResponseBody String user(@AuthenticationPrincipal PrincipalDetails principalDetails){
//        System.out.println("PrincipalDetails : "+principalDetails.getUsername());
//        return "user";
//    }
//
//
//
//}
