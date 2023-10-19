package com.example.burgerqueen_proj.member.service;

import com.example.burgerqueen_proj.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberService memberService;

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        Member member = memberService.findMemberByEmail(userEmail);
        if(member.getMemberId()==1){
            List<SimpleGrantedAuthority> roles = new ArrayList<>(
                    Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));
            member.setAuthorities(roles);
        }else{
            List<SimpleGrantedAuthority> roles = new ArrayList<>(
                    Arrays.asList(new SimpleGrantedAuthority("ROLE_MEMBER")));
            member.setAuthorities(roles);

        }


        System.out.println(">>>>>>>>>>>>");
        System.out.println(member.getAuthorities());
        return member;
    }
}
