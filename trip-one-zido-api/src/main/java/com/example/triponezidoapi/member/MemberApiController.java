package com.example.triponezidoapi.member;

import com.example.triponezidoapi.dto.*;
import com.example.triponezidoapi.dto.request.*;
import com.example.triponezidoapi.dto.response.*;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Validated
@RequestMapping("/api/member")
@Tag(name = "Member", description = "Member API")
public class MemberApiController {
    @Autowired
    MemberService memberService;

    @DeleteMapping("/")
    @Operation(summary = "회원 탈퇴")
    public boolean removeMember(
            @SessionAttribute(name="id")
            @Parameter(description = "로그인 회원 정보")
            Long id,

            @RequestParam
            @Parameter(description = "비밀번호")
            String password,

            @RequestParam
            @Parameter(description = "비밀번호확인")
            String passwordCheck
    ){
        RequestPassword requestPassword = new RequestPassword(id, password, passwordCheck);
        return memberService.deleteMember(requestPassword);
    }

    @GetMapping("/profile")
    @Operation(summary = "프로필 사진 가져오기")
    public ResponseMember showPofile(
            @SessionAttribute(name="id")
            @Parameter(description = "로그인 회원 번호")
            Long sessionId
    ){
        return memberService.getMemberProfile(sessionId);
    }

    @GetMapping("/")
    @Operation(summary = "회원 정보 조회")
    public Member showMemberInfo(
            @SessionAttribute(name="id")
            @Parameter(description = "로그인 회원 번호")
            Long sessionId
    ){
        return memberService.getMember(sessionId);
    }

    @GetMapping("/signup")
    @Operation(summary = "회원가입시 보안질문 조회")
    public List<ResponseQuestions> questionsInfo(){
        return memberService.getSecurityQuestions();
    }

    @GetMapping("/config")
    @Operation(summary = "설정 페이지 조회")
    public ResponseConfigPage showConfig(
            @SessionAttribute(name="id")
            @Parameter(description = "로그인 회원 번호")
            Long SessionId
    ){
        return memberService.getConfig(SessionId);
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

    @PostMapping("/signup")
    @Operation(summary = "회원가입")
    public void signupMember(
            @RequestBody
            @Valid
            @Parameter(description = "회원 등록 정보")
            Member member
    ){
        memberService.signUp(member);
    }

    @PostMapping("/signup/{loginId}")
    @Operation(summary = "아이디 중복확인")
    public boolean signupCheckLoginId(
            @PathVariable
            @Parameter(description = "작성한 아이디")
            String loginId
    ){
        return memberService.isUsingMemberId(loginId);
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

    @PostMapping("/check")
    @Operation(summary = "비밀번호 찾기 - 보안 답변 전송")
    public boolean answerQuestion(
            @RequestBody
            @Parameter(description = "보안 답변")
            RequestAnswer requestAnswer
    ){
        return memberService.checkAnswer(requestAnswer);
    }

    @PostMapping("/check/pw")
    @Operation(summary = "비밀번호 찾기 - 비밀번호를 찾을 회원번호 조회")
    public Long checkPw(
            @RequestBody
            @Parameter(description = "이름 / 이메일 / 아이디")
            RequestFind requestFind
    ){
        return memberService.getId(requestFind);
    }

    @PostMapping("/check/id")
    @Operation(summary = "아이디 찾기")
    public String checkId(
            @RequestBody
            @Parameter(description = "이름 / 이메일")
            RequestFindId requestFindid

    ){
        return memberService.getLoginId(requestFindid);
    }

    @PostMapping("/autoLogin")
    public void autoLogin(
            @RequestBody
            Login login,
            HttpServletRequest request
    ){
        memberService.autoLogin(login, request);
    }

    @PutMapping("/profile")
    @Operation(summary = "프로필 사진 변경")
    public void PutProfile(
            @SessionAttribute(name="id")
            @Parameter(description = "로그인 회원 번호")
            Long sessionId,

            @RequestBody
            @Parameter(description = "프로필 사진")
            RequestPhoto profile
    ){
        profile.setId(sessionId);
        memberService.updateProfile(profile);
    }
    @PutMapping("/password")
    @Operation(summary = "비밀번호 변경")
    public boolean updatePassword(
            @SessionAttribute(name="id")
            @Parameter(description = "로그인 회원 정보")
            Long sessionId,

            @RequestBody
            @Parameter(description = "변경할 비밀번호")
            RequestNewPassword requestNewPassword
    ){
        // 현재 비밀번호 확인
        requestNewPassword.setMemberId(sessionId);
        return memberService.updatePw(requestNewPassword);
    }

    @PutMapping("/passwd/{id}")
    @Operation(summary = "비밀번호 찾기 - 비밀번호 재설정")
    public boolean setNewPassword(
            @PathVariable
            @Parameter(description = "회원 번호")
            Long id,

            @RequestBody
            @Parameter(description = "새로운 비밀번호")
            RequestNewPassword requestNewPassword
    ){
        requestNewPassword.setMemberId(id);
        return memberService.updatePw(requestNewPassword);
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
        member.setId(sessionId);
        memberService.updateMember(member);
    }
}
