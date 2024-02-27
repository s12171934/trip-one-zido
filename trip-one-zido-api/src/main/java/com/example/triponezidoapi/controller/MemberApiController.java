package com.example.triponezidoapi.controller;

import com.example.triponezidoapi.dto.LoginForm;
import com.example.triponezidoapi.dto.Member;
import com.example.triponezidoapi.dto.RequestFind;
import com.example.triponezidoapi.dto.ResponseQuestion;
import com.example.triponezidoapi.mappers.MemberMapper;
import jakarta.validation.constraints.PastOrPresent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberApiController {
    @Autowired
    MemberMapper memberMapper;

    @GetMapping("/questions")
    public List<ResponseQuestion> questionsInfo(){
        List<ResponseQuestion> question = memberMapper.securityQuestions();
        return question;
    }
    @PostMapping("/signup")
    public void signupMember(@RequestBody Member member){
        memberMapper.signUp(member);
    }
    @PostMapping("/login")
    public void loginMember(@RequestBody LoginForm loginForm){
        memberMapper.getLogin(loginForm);
    }
    @PostMapping("/check/id")
    public String checkId(@RequestBody RequestFind requestFind){
        return memberMapper.getLoginIdByFind(requestFind).getLoginId();
    }
    @PostMapping("/check/pw")
    public Long checkPw(@RequestBody RequestFind requestFind){
        return memberMapper.getLoginIdByFind(requestFind).getId();
    }
 /*   @GetMapping("/check/{id}")
    public String checkQuestion(){
        questionsInfo()
    }*/
}
