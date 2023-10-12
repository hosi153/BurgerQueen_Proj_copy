package com.example.burgerqueen_proj.member.dto;

import com.example.burgerqueen_proj.member.entity.Member;
import com.example.burgerqueen_proj.member.entity.Role;
import lombok.Getter;

import javax.persistence.*;

@Getter
public class MemberPostDto {
    private String email;
    private String password;
    private String memberName;
    private String address1;
    private String address2;
    private String address3;
    private String phone;

    public Member toEntity(){
        return Member.builder()
                .email(this.email)
                .password(this.password)
                .userName(this.memberName)
                .address1(this.address1)
                .address2(this.address2)
                .address3(this.address3)
                .phone(this.phone)
                .build();
    }
}
