package com.example.triponezidoapi.controller;

import com.example.triponezidoapi.dto.*;
import com.example.triponezidoapi.dto.request.*;
import com.example.triponezidoapi.dto.response.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/member")
@Tag(name = "Member", description = "Member API")
public class MemberApiController {
    @GetMapping("/signup")
    @Operation(summary = "회원가입시 보안질문 조회")
    public List<ResponseQuestions> questionsInfo(){
        return null;
    }
    @PostMapping("/signup")
    @Operation(summary = "회원가입")
    public void signupMember(
            @RequestBody
            @Parameter(description = "회원 등록 정보")
            Member member
    ){

    }
    @PostMapping("/signup/{loginId}")
    @Operation(summary = "아이디 중복확인")
    public void signupCheckLoginId(
            @PathVariable
            @Parameter(description = "작성한 아이디")
            String loginId
    ){

    }
    @PostMapping("/login")
    @Operation(summary = "로그인")
    public void loginMember(
            @RequestBody
            @Parameter(description = "로그인 정보")
            Login login
    ){

    }
    @PostMapping("/check/id")
    @Operation(summary = "아이디 찾기")
    public String checkId(
            @RequestBody
            @Parameter(description = "이름 / 이메일")
            RequestFind requestFind
    ){
        return null;
    }
    @PostMapping("/check/pw")
    @Operation(summary = "비밀번호 찾기")
    public long checkPw(
            @RequestBody
            @Parameter(description = "이름 / 이메일 / 아이디")
            RequestFind requestFind
    ){
        return 0;
    }
    @GetMapping("/check/{id}")
    @Operation(summary = "비밀번호 찾기 - 선택한 보안 질문 조회")
    public String checkQuestion(
            @PathVariable
            @Parameter(description = "회원 번호")
            long id
    ){
        return null;
    }
    @PostMapping("/check/{id}")
    @Operation(summary = "비밀번호 찾기 - 보안 답변 전송")
    public void answerQuestion(
            @PathVariable
            @Parameter(description = "회원 번호")
            long id,

            @RequestParam
            @Parameter(description = "보안 답변")
            String answer
    ){

    }
    @PutMapping("/passwd/{id}")
    @Operation(summary = "비밀번호 찾기 - 비밀번호 재설정")
    public void setNewPassword(
            @PathVariable
            @Parameter(description = "회원 번호")
            long id,

            @RequestBody
            @Parameter(description = "새로운 비밀번호")
            RequestNewPassword requestNewPassword
    ){

    }
    @GetMapping("/")
    @Operation(summary = "회원 정보 조회")
    public Member showMemberInfo(
            @SessionAttribute(name="id")
            @Parameter(description = "로그인 회원 번호")
            long SessionId
    ){
        return null;
    }
    @PutMapping("/")
    @Operation(summary = "회원 정보 수정")
    public void updateMemberInfo(
            @SessionAttribute(name="id")
            @Parameter(description = "로그인 회원 번호")
            long sessionId,

            @RequestBody
            @Parameter(description = "수정할 회원 정보")
            Member member
    ){

    }
    @PutMapping("/profile")
    @Operation(summary = "프로필 사진 변경")
    public void updateProfile(
            @SessionAttribute(name="id")
            @Parameter(description = "로그인 회원 번호")
            long sessionId,

            @RequestParam
            @Parameter(description = "프로필 사진")
            byte[] profile
    ){

    }
    @PutMapping("/password")
    @Operation(summary = "비밀번호 변경")
    public void updatePassword(
            @SessionAttribute(name="id")
            @Parameter(description = "로그인 회원 정보")
            long sessionId,

            @RequestBody
            @Parameter(description = "변경할 비밀번호")
            RequestNewPassword requestNewPassword
    ){

    }
    @DeleteMapping("/")
    @Operation(summary = "회원 탈퇴")
    public void removeMember(
            @SessionAttribute(name="id")
            @Parameter(description = "로그인 회원 정보")
            long id
    ){

    }
/*    @PostMapping("/pairing")
    public void pairingSignup(@RequestBody ){

    }*/
}
