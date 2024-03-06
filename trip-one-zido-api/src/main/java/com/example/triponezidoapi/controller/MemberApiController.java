package com.example.triponezidoapi.controller;

import com.example.triponezidoapi.dto.*;
import com.example.triponezidoapi.dto.request.*;
import com.example.triponezidoapi.dto.response.*;
import com.example.triponezidoapi.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/member")
@Tag(name = "Member", description = "Member API")
public class MemberApiController {
    @Autowired
    MemberService memberService;

    @GetMapping("/signup")
    @Operation(summary = "회원가입시 보안질문 조회")
    public List<ResponseQuestions> questionsInfo(){
        return memberService.getSecurityQuestions();
    }
    @PostMapping("/signup")
    @Operation(summary = "회원가입")
    public void signupMember(
            @RequestBody
            @Parameter(description = "회원 등록 정보")
            Member member
    ){
        memberService.signUp(member);
    }
    @PostMapping("/signup/{loginId}")
    @Operation(summary = "아이디 중복확인")
    public void signupCheckLoginId(
            @PathVariable
            @Parameter(description = "작성한 아이디")
            String loginId
    ){
        memberService.isUsingMemberId(loginId);
    }
    @PostMapping("/login")
    @Operation(summary = "로그인")
    public boolean loginMember(
            @RequestBody
            @Parameter(description = "로그인 정보")
            Login login,
            HttpServletRequest request
    ){

        return memberService.login(login, request);
    }
    @PostMapping("/check/id")
    @Operation(summary = "아이디 찾기")
    public String checkId(
            @RequestBody
            @Parameter(description = "이름 / 이메일")
            RequestFind requestFind
    ){
        return memberService.getLoginId(requestFind);
    }
    @PostMapping("/check/pw")
    @Operation(summary = "비밀번호 찾기")
    public long checkPw(
            @RequestBody
            @Parameter(description = "이름 / 이메일 / 아이디")
            RequestFind requestFind
    ){
        return memberService.getId(requestFind);
    }
    @GetMapping("/check/{id}")
    @Operation(summary = "비밀번호 찾기 - 선택한 보안 질문 조회")
    public String checkQuestion(
            @PathVariable
            @Parameter(description = "회원 번호")
            Long id
    ){
        return memberService.getQuestion(id);
    }
    @PostMapping("/check/{id}")
    @Operation(summary = "비밀번호 찾기 - 보안 답변 전송")
    public boolean answerQuestion(
            @PathVariable
            @Parameter(description = "회원 번호")
            Long id,

            @RequestParam
            @Parameter(description = "보안 답변")
            String answer
    ){
        return memberService.checkAnswer(id, answer);
    }
    @PutMapping("/passwd/{id}")
    @Operation(summary = "비밀번호 찾기 - 비밀번호 재설정")
    public void setNewPassword(
            @PathVariable
            @Parameter(description = "회원 번호")
            Long id,

            @RequestBody
            @Parameter(description = "새로운 비밀번호")
            RequestNewPassword requestNewPassword
    ){
        memberService.updatePw(id, requestNewPassword);
    }
    @GetMapping("/")
    @Operation(summary = "회원 정보 조회")
    public Member showMemberInfo(
            @SessionAttribute(name="id")
            @Parameter(description = "로그인 회원 번호")
            Long SessionId
    ){
        return memberService.getMember(SessionId);
    }
    @PutMapping("/")
    @Operation(summary = "회원 정보 수정")
    public void updateMemberInfo(
            @SessionAttribute(name="id")
            @Parameter(description = "로그인 회원 번호")
            Long sessionId,

            @RequestBody
            @Parameter(description = "수정할 회원 정보")
            Member member
    ){
        memberService.updateMember(sessionId, member);
    }
    @PutMapping("/profile")
    @Operation(summary = "프로필 사진 변경")
    public void PutProfile(
            @SessionAttribute(name="id")
            @Parameter(description = "로그인 회원 번호")
            Long sessionId,

            @RequestParam
            @Parameter(description = "프로필 사진")
            byte[] profile
    ){
        memberService.updateProfile(sessionId, profile);
    }
    @PutMapping("/password")
    @Operation(summary = "비밀번호 변경")
    public void updatePassword(
            @SessionAttribute(name="id")
            @Parameter(description = "로그인 회원 정보")
            Long sessionId,

            @RequestBody
            @Parameter(description = "변경할 비밀번호")
            RequestNewPassword requestNewPassword
    ){
        memberService.updatePw(sessionId, requestNewPassword);
    }
    @DeleteMapping("/")
    @Operation(summary = "회원 탈퇴")
    public void removeMember(
            @SessionAttribute(name="id")
            @Parameter(description = "로그인 회원 정보")
            Long id
    ){
        memberService.deleteMember(id);
    }
/*    @PostMapping("/pairing")
    public void pairingSignup(@RequestBody ){

    }*/
}
