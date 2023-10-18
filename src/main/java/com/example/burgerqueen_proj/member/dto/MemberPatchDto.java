package com.example.burgerqueen_proj.member.dto;

import com.example.burgerqueen_proj.member.entity.Member;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class MemberPatchDto {
    @ApiModelProperty(example = "4567")
    private String password;
    @ApiModelProperty(example = "김유플")
    private String memberName;
    private String address1;
    private String address2;
    private String address3;
    @ApiModelProperty(example = "010-1234-5678")
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
