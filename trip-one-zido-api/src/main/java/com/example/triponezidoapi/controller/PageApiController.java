package com.example.triponezidoapi.controller;

import com.example.triponezidoapi.dto.response.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/page")
public class PageApiController {
    @GetMapping("/{id}")
    @Tag(name = "Member")
    @Operation(summary = "회원 페이지 조회")
    public ResponseMemberPage showMemberPage(
            @PathVariable(required = false)
            @Schema(nullable = true)
            @Parameter(description = "페이지 소유 회원 번호")
            long id,

            @SessionAttribute(name="id")
            @Parameter(description = "로그인 회원 번호")
            long sessionId
    ){
        return null;
    }
    @GetMapping("/following/{id}")
    @Tag(name = "Follow", description = "Follow API")
    @Operation(summary = "팔로잉 명단")
    public ResponseMember listFollowing(
            @PathVariable(required = false)
            @Schema(nullable = true)
            @Parameter(description = "대상 회원 번호")
            long id,

            @SessionAttribute(name="id")
            @Parameter(description = "로그인 회원 번호")
            long sessionId
    ){
        return null;
    }
    @GetMapping("/follower/{id}")
    @Tag(name = "Follow")
    @Operation(summary = "팔로워 명단")
    public ResponseMember listFollower(
            @PathVariable(required = false)
            @Schema(nullable = true)
            @Parameter(description = "대상 회원 번호")
            long id,

            @SessionAttribute(name="id")
            @Parameter(description = "로그인 회원 번호")
            long sessionId
    ){
        return null;
    }
    @PostMapping("/follow/{id}")
    @Tag(name = "Follow")
    @Operation(summary = "팔로우")
    public void followingMember(
            @PathVariable(required = false)
            @Parameter(description = "대상 회원 번호")
            long id,

            @SessionAttribute(name="id")
            @Parameter(description = "로그인 회원 번호")
            long sessionId
    ){

    }
    @DeleteMapping("/follow/{id}")
    @Tag(name = "Follow")
    @Operation(summary = "언팔로우")
    public void unfollowMember(
            @PathVariable(required = false)
            @Parameter(description = "대상 회원 번호")
            long id,

            @SessionAttribute(name="id")
            @Parameter(description = "로그인 회원 번호")
            long sessionId
    ){

    }
}
