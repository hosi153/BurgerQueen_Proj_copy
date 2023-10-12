package com.example.burgerqueen_proj.member.controller;

import com.example.burgerqueen_proj.member.dto.MemberPostDto;
import com.example.burgerqueen_proj.member.dto.MemberResponseDto;
import com.example.burgerqueen_proj.member.entity.Member;
import com.example.burgerqueen_proj.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/member")
public class MemberController {
    private final MemberService memberService;

    //CREATE : 가입
    @PostMapping("/new")
    public ResponseEntity joinMember(@RequestBody MemberPostDto memberPostDto){
        Member member = memberService.joinMember(memberPostDto.toEntity());
        return new ResponseEntity(new MemberResponseDto(member), HttpStatus.CREATED);
    }

    //READ : 로그인
    @GetMapping("/{member-email}")
    public ResponseEntity loginMember(@PathVariable("member-id") String email){
        Member member = memberService.findMemberByEmail(email);
        return new ResponseEntity(new MemberResponseDto(member), HttpStatus.OK);
    }

    //UPDATE : 고객정보 변경


    //DELETE : 고객삭제

}
