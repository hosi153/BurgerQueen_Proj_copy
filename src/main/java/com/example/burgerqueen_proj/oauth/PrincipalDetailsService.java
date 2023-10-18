//package com.example.burgerqueen_proj.oauth;
//
//import com.example.burgerqueen_proj.member.entity.Member;
//import com.example.burgerqueen_proj.member.repository.MemberRepository;
//import com.example.burgerqueen_proj.member.service.MemberService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class PrincipalDetailsService implements UserDetailsService {
//
//
//    private  MemberRepository memberRepository;
//    private MemberService memberService;
//
//    public PrincipalDetailsService(MemberRepository memberRepository, MemberService memberService) {
//        this.memberRepository = memberRepository;
//        this.memberService = memberService;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        Member byEmail = memberService.findMemberByEmailForOAuth(email);
//        if (byEmail != null){
//            return new PrincipalDetails(byEmail);
//        }
//        return null;
//    }
//}
