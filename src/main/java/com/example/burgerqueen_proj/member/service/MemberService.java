package com.example.burgerqueen_proj.member.service;

import com.example.burgerqueen_proj.exception.BusinessLogicException;
import com.example.burgerqueen_proj.exception.ExceptionCode;
import com.example.burgerqueen_proj.member.entity.Member;
import com.example.burgerqueen_proj.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;



    public Member findUser(long userId) {
        return findVerifiedUser(userId);
    }

    public Member findVerifiedUser(long userId) {
        Optional<Member> optionalMember =
                memberRepository.findById(userId);
        Member findMember =
                optionalMember.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));
        return findMember;
    }


}
