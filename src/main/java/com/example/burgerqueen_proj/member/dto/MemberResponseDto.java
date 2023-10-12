package com.example.burgerqueen_proj.member.dto;

import com.example.burgerqueen_proj.member.entity.Member;
import com.example.burgerqueen_proj.member.entity.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.OneToOne;

@Getter
@Setter
public class MemberResponseDto {

    private long memberId;
    private String email;
    private String memberName;
    private String address1;
    private String address2;
    private String address3;
    private String phone;
    private int stamp;
    private Member.MemberGrade grade;

    public MemberResponseDto(Member member){
        this.memberId = member.getMemberId();
        this.email = member.getEmail();
        this.memberName = member.getUserName();
        this.address1 = member.getAddress1();
        this.address2 = member.getAddress2();
        this.address3 = member.getAddress3();
        this.phone = member.getPhone();
        this.stamp = member.getStamp();
        this.grade = member.getGrade();

    }

}
