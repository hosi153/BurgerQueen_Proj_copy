package com.example.burgerqueen_proj.member.dto;

import com.example.burgerqueen_proj.member.entity.Member;
import com.example.burgerqueen_proj.member.entity.Role;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Getter;

import javax.persistence.*;

@Getter
public class MemberPostDto {
    @ApiModelProperty(example = "swaggerTest@test.com", required = true)
    private String email;
    @ApiModelProperty(example = "12345", required = true)
    private String password;
    @ApiModelProperty(example = "김유플", required = true)
    private String memberName;
    private String address1;
    private String address2;
    private String address3;
    @ApiModelProperty(example = "010-8080-1234")
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
