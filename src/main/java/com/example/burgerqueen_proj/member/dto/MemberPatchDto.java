package com.example.burgerqueen_proj.member.dto;

import com.example.burgerqueen_proj.member.entity.Member;
import lombok.Getter;

@Getter
public class MemberPatchDto {
    private String password;
    private String memberName;
    private String address1;
    private String address2;
    private String address3;
    private String phone;

    public Member toEntity(String email){
        return Member.builder()
                .email(email)
                .password(this.password)
                .userName(this.memberName)
                .address1(this.address1)
                .address2(this.address2)
                .address3(this.address3)
                .phone(this.phone)
                .build();
    }
}
