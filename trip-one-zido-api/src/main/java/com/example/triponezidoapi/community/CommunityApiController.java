package com.example.triponezidoapi.community;

import com.example.triponezidoapi.dto.request.*;
import com.example.triponezidoapi.dto.response.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/community")
@Tag(name = "Community", description = "Community API")
public class CommunityApiController {
    @Autowired
    CommunityService communityService;

    @DeleteMapping("/{id}")
    @Operation(summary = "커뮤니티 게시물 삭제")
    public void deleteCommunity(
            @PathVariable
            @Parameter(description = "커뮤니티 게시물 번호")
            Long id
    ){
        communityService.deleteCommunity(id);
    }
    @DeleteMapping("/member/{id}")
    @Operation(summary = "커뮤니티 참여 취소하기")
    public void departureCommunity(
            @PathVariable
            @Parameter(description = "커뮤니티 게시물 번호")
            Long id,

            @SessionAttribute(name = "id")
            @Parameter(description = "로그인 회원 번호")
            Long sessionId
    ){
        RequestContentMember requestContentMember = new RequestContentMember();
        requestContentMember.setContentId(id);
        requestContentMember.setMemberId(sessionId);
        communityService.deleteOwner(requestContentMember);
    }
    @GetMapping("/{id}")
    @Operation(summary = "커뮤니티 상세 조회")
    public ResponseCommunityDetail showCommunityDetail(
            @PathVariable
            @Parameter(description = "커뮤니티 게시물 번호")
            Long id,

            @SessionAttribute(name = "id")
            @Parameter(description = "로그인 회원 정보")
            Long sessionId
    ){
        RequestContentMember requestContentMember = new RequestContentMember();
        requestContentMember.setContentId(id);
        requestContentMember.setMemberId(sessionId);
        return communityService.getCommunity(requestContentMember);
    }
    @GetMapping("/list/{page}")
    @Operation(summary = "커뮤니티 목록 조회")
    public ResponseCommunityList showCommunityAll(
            @PathVariable
            @Parameter(description = "페이지 번호")
            long page
    ){
        return communityService.getCommunityList(page);
    }
    @PostMapping("/search/{page}")
    @Operation(summary = "검색한 커뮤니티 게시물 목록")
    public ResponseCommunityList searchCommunity(
            @RequestBody
            @Parameter(description = "검색 정보")
            RequestCommunitySearch requestCommunitySearch,

            @PathVariable
            @Parameter(description = "페이지 번호")
            long page
    ){
        return communityService.getCommunityListWithSearch(requestCommunitySearch,page);
    }
    @PostMapping("/member/{id}")
    @Operation(summary = "커뮤니티 참여하기")
    public void entryCommunity(
            @PathVariable
            @Parameter(description = "커뮤니티 게시물 번호")
            Long id,

            @SessionAttribute(name = "id")
            @Parameter(description = "로그인 회원 번호")
            Long sessionId
    ){
        RequestContentMember requestContentMember = new RequestContentMember();
        requestContentMember.setContentId(id);
        requestContentMember.setMemberId(sessionId);
        communityService.addOwner(requestContentMember);
    }
    @PostMapping("/")
    @Operation(summary = "커뮤니티 게시물 등록")
    public void postCommunity(
            @RequestBody
            @Parameter(description = "커뮤니티 게시물 정보")
            RequestCommunity requestCommunity,

            @SessionAttribute(name = "id")
            @Parameter(description = "로그인 회원 정보")
            Long sessionId
    ){
        communityService.addCommunity(requestCommunity,sessionId);
    }
    @PutMapping("/{id}")
    @Operation(summary = "커뮤니티 게시물 수정")
    public void putCommunity(
            @RequestBody
            @Parameter(description = "커뮤니티 게시물 정보")
            RequestCommunity requestCommunity,

            @PathVariable
            @Parameter(description = "커뮤니티 게시물 번호")
            Long id
    ){
        requestCommunity.setId(id);
        communityService.updateCommunity(requestCommunity);
    }
}