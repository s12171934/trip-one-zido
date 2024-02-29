package com.example.triponezidoapi.controller;

import com.example.triponezidoapi.dto.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberApiController {
    @GetMapping("/signup")
    public List<ResponseQuestion> questionsInfo(){
        return null;
    }
    @PostMapping("/signup")
    public void signupMember(@RequestBody Member member){

    }
    @PostMapping("/login")
    public void loginMember(@RequestBody RequestLogin requestLogin){
        memberMapper.getLogin(requestLogin);
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
    public void setNewPassword(@PathVariable long id, @RequestBody RequestPassword requestPassword){

    }
    @GetMapping("/")
    public Member showMemberInfo(long id){
        return null;
    }
    @PutMapping("/")
    public void updateMemberInfo(long id, @RequestBody Member member){

    }
    @PutMapping("/profile")
    public void updateProfile(long id, @RequestParam byte[] profile){

    }
    @PutMapping("/password")
    public void updatePassword(long id, @RequestBody RequestPassword requestPassword){

    }
    @DeleteMapping("/")
    public void removeMember(long id){

    }
/*    @PostMapping("/pairing")
    public void pairingSignup(@RequestBody ){

    }*/
}
