package com.example.burgerqueen_proj.member.service;

import com.example.burgerqueen_proj.cart.entity.Cart;
import com.example.burgerqueen_proj.cart.service.CartService;
import com.example.burgerqueen_proj.exception.BusinessLogicException;
import com.example.burgerqueen_proj.exception.ExceptionCode;
import com.example.burgerqueen_proj.member.entity.Member;
import com.example.burgerqueen_proj.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.Transient;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Component
public class MemberService{

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    //private final CartService cartService;


    public MemberService(MemberRepository memberRepository, BCryptPasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Member findMember(long memberId) {

        return findVerifiedMember(memberId);
    }

    public Member findVerifiedMember(long memberId) {
        Optional<Member> optionalMember =
                memberRepository.findById(memberId);
        Member findMember =
                optionalMember.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));
        return findMember;
    }




    @Transactional
    public Member joinMember(Member member) {
        //이메일 기준으로 존재하는 사용자인지 확인
        verifyExistsEmail(member.getEmail());
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        Member joinedMember = memberRepository.save(member);

        //카트 생성
        joinedMember.setCart(new Cart());

        return joinedMember;
    }


    private void verifyExistsEmail(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        if(member.isPresent())
            throw new BusinessLogicException(ExceptionCode.MEMBER_EXISTS);
    }


    public Member findMemberByEmail(String email) {
        Optional<Member> optionalMember = memberRepository.findByEmail(email);
        Member findMember = optionalMember.orElseThrow(()->new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));
        return findMember;
    }
    public Member findMemberByEmailForOAuth(String email) {
        try {        Optional<Member> optionalMember = memberRepository.findByEmail(email);
            Member member = findMember(optionalMember.get().getMemberId());
            return member;



        }catch (Exception e){
            return null;
        }

    }

    public void deleteMember(String email) {
        Member findMember = findMemberByEmail(email);
        memberRepository.delete(findMember);
    }

    public Member updateMember(Member member) {
        System.out.println(member.getAddress2());
        Member updatemember = findMemberByEmail(member.getEmail());
        updatemember.update(member);
        return memberRepository.save(updatemember);
    }

}
