package com.example.triponezidoapi.controller;

import com.example.triponezidoapi.dto.*;
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
    public List<ResponseQuestion> questionsInfo(){
        return memberMapper.securityQuestions();
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
        return memberMapper.getId(requestFind);
    }
    @GetMapping("/check/{id}")
    public String checkQuestion(@PathVariable Long id){
        memberMapper.getSecurityQuestion(id);
        return memberMapper.getSecurityAnswer(id);
    }
    @PutMapping("/passwd/{id}")
    public void setNewPassword(@PathVariable Long id, @RequestBody RequestPassword requestPassword){
        Password password = new Password();
        password.setId(id);
        password.setPassword(requestPassword.getChangePassword());
        memberMapper.updatePassword(password);
    }
    @GetMapping("/")
    public Member showMember(Long id){
        return memberMapper.getMemberById(id);
    }
    @PutMapping("/")
    public void changeMember(@RequestBody Member member){
        memberMapper.updateMember(member);
    }
    @DeleteMapping("/")
    public void removeMember(Long id){
        memberMapper.deleteMember(id);
    }
    @PutMapping("/profile")
    public void changeProfile(@RequestBody ProfileMember profileMember, Long id){
        profileMember.setId(id);
        memberMapper.updateProfile(profileMember);
    }
    @PutMapping("/password")
    public void changePassword(Long id, @RequestBody RequestPassword requestPassword){
        Password password = new Password();
        password.setId(id);
        password.setPassword(requestPassword.getChangePassword());
        memberMapper.updatePassword(password);
    }
/*    @PostMapping("/pairing")
    public void pairingSignup(@RequestBody ){

    }*/
    @PostMapping("/follow/{id}")
    public void followingMember(Long me, @PathVariable Long id){
        Follow follow = new Follow();
        follow.setFollower(me);
        follow.setFollowing(id);
        memberMapper.follow(follow);
    }
    @GetMapping("/following/{id}")
    public int countFollowing(@PathVariable Long id){
        return memberMapper.followingCount(id);
    }
    @GetMapping("/following/{id}/list")
    public List<ProfileMember> listFollowing(@PathVariable Long id){
        return memberMapper.followingList(id);
    }
    @GetMapping("/follower/{id}")
    public int countFollower(@PathVariable Long id){
        return memberMapper.followerCount(id);
    }
    @GetMapping("/follower/{id}/list")
    public List<ProfileMember> listFollower(@PathVariable Long id){
        return memberMapper.followerList(id);
    }
    @DeleteMapping("/unfollow/{id}")
    public void unfollowMember(Long me, @PathVariable Long id){
        Follow follow = new Follow();
        follow.setFollower(me);
        follow.setFollowing(id);
        memberMapper.unFollow(follow);
    }

}
