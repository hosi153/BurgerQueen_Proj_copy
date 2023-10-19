package com.example.burgerqueen_proj.member.controller;

import com.example.burgerqueen_proj.exception.BusinessLogicException;
import com.example.burgerqueen_proj.member.dto.MemberLoginDto;
import com.example.burgerqueen_proj.member.dto.MemberPatchDto;
import com.example.burgerqueen_proj.member.dto.MemberPostDto;
import com.example.burgerqueen_proj.member.dto.MemberResponseDto;
import com.example.burgerqueen_proj.member.entity.Member;
import com.example.burgerqueen_proj.member.service.MemberService;
import com.nimbusds.oauth2.sdk.ErrorResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/member")
@Api(tags="사용자정보 관련 API", description="버거퀸 서비스 사용자 관련 API(생성, 조회, 업데이트 등)")
public class MemberController {
    private final MemberService memberService;

    /**
     * 1.1 사용자 신규 생성 API
     * [POST] /api/member/new
     */
    @ApiOperation(value="사용자 생성(가입)", notes="신규 가입 시 사용하는 API")
    @ApiImplicitParams({
            @ApiImplicitParam(
                name = "email",
                value = "사용자 이메일 정보",
                required = true),
            @ApiImplicitParam(
                name = "password",
                value = "사용자 비밀번호"),
            @ApiImplicitParam(
                name = "memberName",
                required = true,
                value = "사용자 이름"),
            @ApiImplicitParam(
                name = "phone",
                value = "사용자 전화번호")
    })
    @PostMapping("/new")
    public ResponseEntity joinMember(@RequestBody MemberPostDto memberPostDto){
        Member member = memberService.joinMember(memberPostDto.toEntity());
        return new ResponseEntity(new MemberResponseDto(member), HttpStatus.CREATED);
    }

    /**
     * 1.2 사용자 조회 API
     * [GET] /api/member/{email}
     */
    @ApiOperation(value="사용자 조회", notes="사용자 ID(이메일)로 사용자 정보 조회")
    @ApiImplicitParam(
            name = "email",
            value = "사용자 이메일 정보",
            paramType = "path",
            required = true)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "사용자 조회 성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 사용자") })
    @GetMapping("/{email}")
    public ResponseEntity loginMember(@PathVariable("email") String email){
        Member member = memberService.findMemberByEmail(email);
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("Authority>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(principal);
        return new ResponseEntity(new MemberResponseDto(member), HttpStatus.OK);
    }

    /**
     * 1.3 사용자 정보 변경 API
     * [PATCH] /api/member/{email}
     */
    @ApiOperation(value="사용자 정보 변경", notes="정보 변경 시 사용하는 API")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "email",
                    value = "사용자 이메일 정보",
                    paramType = "path",
                    required = true),
            @ApiImplicitParam(
                    name = "password",
                    value = "사용자 비밀번호"),
            @ApiImplicitParam(
                    name = "address1",
                    value = "배달 주소"),
            @ApiImplicitParam(
                    name = "phone",
                    value = "사용자 전화번호")
    })
    @PatchMapping("/{email}")
    public ResponseEntity updateMember(@PathVariable("email")String email, @RequestBody MemberPatchDto memberPatchDto){
        Member member = memberService.updateMember(memberPatchDto.toEntity(email));
        return new ResponseEntity(new MemberResponseDto(member), HttpStatus.OK);
    }


    @ApiOperation(value="사용자 삭제(탈퇴)", notes="사용자 ID(이메일)로 사용자 정보 삭제")
    @ApiImplicitParam(
            name = "email",
            value = "사용자 이메일 정보",
            paramType = "path",
            required = true)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "사용자 삭제되어 없는 사용자") })
    @DeleteMapping("/{member-email}")
    public ResponseEntity deleteMember(@PathVariable("member-email")String email){
        memberService.deleteMember(email);
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

}
