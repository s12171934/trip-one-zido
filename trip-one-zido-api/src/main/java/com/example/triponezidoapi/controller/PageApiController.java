package com.example.triponezidoapi.controller;

import com.example.triponezidoapi.dto.response.ResponseMember;
import com.example.triponezidoapi.dto.response.ResponseMemberPage;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/page")
public class PageApiController {
    @GetMapping("/{id}")
    public ResponseMemberPage showMemberPage(@PathVariable long id, long sessionId){
        return null;
    }
    @GetMapping("/following/{id}")
    public ResponseMember listFollowing(@PathVariable long id, long sessionId){
        return null;
    }
    @GetMapping("/follower/{id}")
    public ResponseMember listFollower(@PathVariable long id, long sessionId){
        return null;
    }
    @PostMapping("/follow/{id}")
    public void followingMember(@PathVariable long id, long sessionId){

    }
    @DeleteMapping("/follow/{id}")
    public void unfollowMember(@PathVariable long id, long sessionId){

    }
}
