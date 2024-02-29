package com.example.triponezidoapi.controller;

import com.example.triponezidoapi.dto.*;
import com.example.triponezidoapi.dto.Request.RequestFind;
import com.example.triponezidoapi.dto.Request.RequestLogin;
import com.example.triponezidoapi.dto.Request.RequestPassword;
import com.example.triponezidoapi.dto.Response.ResponseMember;
import com.example.triponezidoapi.dto.Response.ResponseQuestions;
import com.example.triponezidoapi.dto.beforeUse.Follow;
import com.example.triponezidoapi.dto.beforeUse.Password;
import com.example.triponezidoapi.mappers.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberApiController {
    @Autowired
    MemberMapper memberMapper;

    @GetMapping("/questions")
    public List<ResponseQuestions> questionsInfo(){
        return memberMapper.securityQuestions();
    }
    @PostMapping("/signup")
    public void signupMember(@RequestBody Member member){
        memberMapper.signUp(member);
    }
    @PostMapping("/login")
    public void loginMember(@RequestBody RequestLogin requestLogin){
        memberMapper.getLogin(requestLogin);
    }
    @PostMapping("/check/id")
    public String checkId(@RequestBody RequestFind requestFind){
        return memberMapper.getLoginIdByFind(requestFind).getLoginId();
    }
    @PostMapping("/check/pw")
    public long checkPw(@RequestBody RequestFind requestFind){
        return memberMapper.getId(requestFind);
    }
    @GetMapping("/check/{id}")
    public String checkQuestion(@PathVariable long id){
        memberMapper.getSecurityQuestion(id);
        return memberMapper.getSecurityAnswer(id);
    }
    @PutMapping("/passwd/{id}")
    public void setNewPassword(@PathVariable long id, @RequestBody RequestPassword requestPassword){
        Password password = new Password();
        password.setId(id);
        password.setPassword(requestPassword.getChangePassword());
        memberMapper.updatePassword(password);
    }
    @GetMapping("/")
    public Member showMember(long id){
        return memberMapper.getMemberById(id);
    }
    @PutMapping("/")
    public void changeMember(@RequestBody Member member){
        memberMapper.updateMember(member);
    }
    @DeleteMapping("/")
    public void removeMember(long id){
        memberMapper.deleteMember(id);
    }
    @PutMapping("/profile")
    public void changeProfile(@RequestBody ResponseMember responseMember, long id){
        responseMember.setId(id);
        memberMapper.updateProfile(responseMember);
    }
    @PutMapping("/password")
    public void changePassword(long id, @RequestBody RequestPassword requestPassword){
        Password password = new Password();
        password.setId(id);
        password.setPassword(requestPassword.getChangePassword());
        memberMapper.updatePassword(password);
    }
/*    @PostMapping("/pairing")
    public void pairingSignup(@RequestBody ){

    }*/
    @PostMapping("/follow/{id}")
    public void followingMember(long me, @PathVariable long id){
        Follow follow = new Follow();
        follow.setFollower(me);
        follow.setFollowing(id);
        memberMapper.follow(follow);
    }
    @GetMapping("/following/{id}")
    public int countFollowing(@PathVariable long id){
        return memberMapper.followingCount(id);
    }
    @GetMapping("/following/{id}/list")
    public List<ResponseMember> listFollowing(@PathVariable long id){
        return memberMapper.followingList(id);
    }
    @GetMapping("/follower/{id}")
    public int countFollower(@PathVariable long id){
        return memberMapper.followerCount(id);
    }
    @GetMapping("/follower/{id}/list")
    public List<ResponseMember> listFollower(@PathVariable long id){
        return memberMapper.followerList(id);
    }
    @DeleteMapping("/unfollow/{id}")
    public void unfollowMember(long me, @PathVariable long id){
        Follow follow = new Follow();
        follow.setFollower(me);
        follow.setFollowing(id);
        memberMapper.unFollow(follow);
    }

}
