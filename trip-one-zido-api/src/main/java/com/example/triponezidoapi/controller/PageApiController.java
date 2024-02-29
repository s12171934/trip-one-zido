package com.example.triponezidoapi.controller;

import com.example.triponezidoapi.dto.response.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/page")
public class PageApiController {
    @GetMapping("/{id}")
    public ResponseMemberPage showMemberPage(
            @PathVariable(required = false)
            long id,

            @SessionAttribute(name="id")
            long sessionId){
        return null;
    }
    @GetMapping("/following/{id}")
    public ResponseMember listFollowing(
            @PathVariable(required = false)
            long id,

            @SessionAttribute(name="id")
            long sessionId){
        return null;
    }
    @GetMapping("/follower/{id}")
    public ResponseMember listFollower(
            @PathVariable(required = false)
            long id,

            @SessionAttribute(name="id")
            long sessionId){
        return null;
    }
    @PostMapping("/follow/{id}")
    public void followingMember(
            @PathVariable(required = false)
            long id,

            @SessionAttribute(name="id")
            long sessionId){

    }
    @DeleteMapping("/follow/{id}")
    public void unfollowMember(
            @PathVariable(required = false)
            long id,

            @SessionAttribute(name="id")
            long sessionId){

    }
}
