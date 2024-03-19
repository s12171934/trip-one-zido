package com.example.triponezidoapi.controller;

import com.example.triponezidoapi.dto.request.RequestGood;
import com.example.triponezidoapi.dto.request.RequestPlan;
import com.example.triponezidoapi.dto.response.*;
import com.example.triponezidoapi.service.ContentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/content")
public class ContentApiController {
    @Autowired
    ContentService contentService;

    @PostMapping("/pin/{id}")
    @Tag(name = "Pin", description = "Pin API")
    @Operation(summary = "게시물 고정하기")
    public void postPin(
            @PathVariable
            @Parameter(description = "고정 게시물 번호")
            Long id,

            @SessionAttribute(name = "id")
            @Parameter(description = "로그인 회원 번호")
            Long sessionId
    ){
        contentService.addPin(id, sessionId);
    }
    @DeleteMapping("/pin/{id}")
    @Tag(name = "Pin")
    @Operation(summary = "게시물 고정 취소하기")
    public void deletePin(
            @PathVariable
            @Parameter(description = "고정 게시물 번호")
            Long id,

            @SessionAttribute(name = "id")
            @Parameter(description = "로그인 회원 번호")
            Long sessionId
    ){
        contentService.deletePin(id, sessionId);
    }

    @GetMapping("/good/{id}")
    @Tag(name = "Good", description = "Good API")
    @Operation(summary = "좋아요 여부 조회")
    public Boolean getGood(
            @PathVariable
            @Parameter(description = "좋아요 게시물 번호")
            Long id,

            @SessionAttribute(name = "id")
            @Parameter(description = "로그인 회원 정보")
            Long sessionId
    ){
        return contentService.isGood(id, sessionId);
    }

    @PostMapping("/good/{id}")
    @Tag(name = "Good")
    @Operation(summary = "좋아요 등록/수정/취소")
    public void postGood(
            @PathVariable
            @Parameter(description = "좋아요 게시물 번호")
            Long id,

            @SessionAttribute(name = "id")
            @Parameter(description = "로그인 회원 정보")
            Long sessionId,

            @RequestBody
            @Parameter(description = "좋아요")
            RequestGood requestGood
    ){
        contentService.addGood(id, sessionId, requestGood);
    }
  
    @GetMapping("/recent-view/")
    @Tag(name = "Content", description = "Content API")
    @Operation(summary = "최근 본 게시물 조회")
    public ResponseRecentView showRecentView(
            @SessionAttribute(name = "id")
            @Parameter(description = "로그인 회원 정보")
            Long sessionId
    ){
        return contentService.getRecentViewPage(sessionId);
    }
    @GetMapping("/recent-view/{page}")
    @Tag(name = "Content", description = "Content API")
    @Operation(summary = "최근 본 게시물 조회")
    public List<ResponseContentList> showMoreRecentView(
            @SessionAttribute(name = "id")
            @Parameter(description = "로그인 회원 정보")
            Long sessionId,

            @PathVariable
            @Parameter(description = "페이지 번호")
            long page

    ){
        return contentService.getRecentView(sessionId,page);
    }
}
