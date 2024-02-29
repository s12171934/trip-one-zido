package com.example.triponezidoapi.controller;

import com.example.triponezidoapi.dto.*;
import com.example.triponezidoapi.dto.request.RequestFind;
import com.example.triponezidoapi.dto.Login;
import com.example.triponezidoapi.dto.request.RequestNewPassword;
import com.example.triponezidoapi.dto.response.ResponseQuestions;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberApiController {
    @GetMapping("/signup")
    public List<ResponseQuestions> questionsInfo(){
        return null;
    }
    @PostMapping("/signup")
    public void signupMember(@RequestBody Member member){

    }
    @PostMapping("/login")
    public void loginMember(@RequestBody Login login){

    }
    @PostMapping("/check/id")
    public String checkId(@RequestBody RequestFind requestFind){
        return null;
    }
    @PostMapping("/check/pw")
    public long checkPw(@RequestBody RequestFind requestFind){
        return 0;
    }
    @GetMapping("/check/{id}")
    public String checkQuestion(@PathVariable long id){
        return null;
    }
    @PostMapping("/chech/{id}")
    public void answerQuestion(@PathVariable long id, @RequestParam String answer){

    }
    @PutMapping("/passwd/{id}")
    public void setNewPassword(@PathVariable long id, @RequestBody RequestNewPassword requestNewPassword){

    }
    @GetMapping("/")
    public Member showMemberInfo(@SessionAttribute(name="id") long id){
        return null;
    }
    @PutMapping("/")
    public void updateMemberInfo(@SessionAttribute(name="id") long id, @RequestBody Member member){

    }
    @PutMapping("/profile")
    public void updateProfile(@SessionAttribute(name="id") long id, @RequestParam byte[] profile){

    }
    @PutMapping("/password")
    public void updatePassword(@SessionAttribute(name="id") long id, @RequestBody RequestPassword requestNewPassword){

    }
    @DeleteMapping("/")
    public void removeMember(@SessionAttribute(name="id") long id){

    }
/*    @PostMapping("/pairing")
    public void pairingSignup(@RequestBody ){

    }*/
}
