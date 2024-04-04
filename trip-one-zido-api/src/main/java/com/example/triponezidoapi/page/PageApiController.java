package com.example.triponezidoapi.page;

import com.example.triponezidoapi.dto.request.RequestFollow;
import com.example.triponezidoapi.dto.request.RequestSessionTarget;
import com.example.triponezidoapi.dto.response.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/page")
public class PageApiController {
    @Autowired
    PageService pageService;

    @DeleteMapping("/follow/{id}")
    @Tag(name = "Follow")
    @Operation(summary = "언팔로우")
    public void unfollowMember(
            @PathVariable(required = false)
            @Parameter(description = "대상 회원 번호")
            Long id,

            @SessionAttribute(name="id")
            @Parameter(description = "로그인 회원 번호")
            Long sessionId
    ){
        RequestFollow requestFollow = new RequestFollow(sessionId, id);

        pageService.unFollow(requestFollow);
    }

    @GetMapping("/following/{id}")
    @Tag(name = "Follow", description = "Follow API")
    @Operation(summary = "팔로잉 명단")
    public List<ResponseMember> listFollowing(
            @PathVariable(required = false)
            @Schema(nullable = true)
            @Parameter(description = "대상 회원 번호")
            Long id,

            @SessionAttribute(name="id")
            @Parameter(description = "로그인 회원 번호")
            Long sessionId
    ){
        if(id == null){
            id = sessionId;
        }

        RequestSessionTarget requestSessionTarget = new RequestSessionTarget();
        requestSessionTarget.setMyMemberId(sessionId);
        requestSessionTarget.setTargetId(id);

        return pageService.getFollowingList(requestSessionTarget);
    }
    @GetMapping("/follower/{id}")
    @Tag(name = "Follow")
    @Operation(summary = "팔로워 명단")
    public List<ResponseMember> listFollower(
            @PathVariable(required = false)
            @Schema(nullable = true)
            @Parameter(description = "대상 회원 번호")
            Long id,

            @SessionAttribute(name="id")
            @Parameter(description = "로그인 회원 번호")
            Long sessionId
    ){
        if(id == null){
            id = sessionId;
        }

        RequestSessionTarget requestSessionTarget = new RequestSessionTarget();
        requestSessionTarget.setMyMemberId(sessionId);
        requestSessionTarget.setTargetId(id);

        return pageService.getFollowerList(requestSessionTarget);
    }

    @PostMapping("/follow/{id}")
    @Tag(name = "Follow")
    @Operation(summary = "팔로우")
    public void followingMember(
            @PathVariable(required = false)
            @Parameter(description = "대상 회원 번호")
            Long id,

            @SessionAttribute(name="id")
            @Parameter(description = "로그인 회원 번호")
            Long sessionId
    ){
        RequestFollow requestFollow = new RequestFollow(sessionId, id);

        pageService.follow(requestFollow);
    }

    @GetMapping(value = {"/{id}" , "/"})
    @Tag(name = "Member")
    @Operation(summary = "회원 페이지 조회")
    public ResponseMemberPage showMemberPage(
            @PathVariable(required = false)
            @Schema(nullable = true)
            @Parameter(description = "페이지 소유 회원 번호")
            Long id,

            @SessionAttribute(name="id")
            @Parameter(description = "로그인 회원 번호")
            Long sessionId
    ){
        if(id == null){
            id = sessionId;
        }

        RequestSessionTarget requestSessionTarget = new RequestSessionTarget();
        requestSessionTarget.setMyMemberId(sessionId);
        requestSessionTarget.setTargetId(id);

        return pageService.getMemberPage(requestSessionTarget);
    }
    @GetMapping("/{id}/plan/{page}")
    @Tag(name = "More")
    @Operation(summary = "회원 페이지 일정 게시물 더보기 조회")
    public List<ResponseContentList> showPlanListByPage(
            @PathVariable
            @Parameter(description = "페이지 소유 회원 번호")
            Long id,

            @SessionAttribute(name="id")
            @Parameter(description = "로그인 회원 번호")
            Long sessionId,

            @PathVariable
            @Parameter(description = "페이징 번호")
            long page,

            @RequestParam(required = false)
            String sort
    ){
        RequestSessionTarget requestSessionTarget = new RequestSessionTarget();
        requestSessionTarget.setMyMemberId(sessionId);
        requestSessionTarget.setTargetId(id);
        requestSessionTarget.setPage(page * 6);
        requestSessionTarget.setSort(sort == null ? "created_at DESC" : sort);

        return pageService.getPlanListByPage(requestSessionTarget);
    }
    @GetMapping("/{id}/spot/{page}")
    @Tag(name = "More")
    @Operation(summary = "회원 페이지 장소 게시물 더보기 조회")
    public List<ResponseContentList> showSpotListByPage(
            @PathVariable
            @Parameter(description = "페이지 소유 회원 번호")
            Long id,

            @SessionAttribute(name="id")
            @Parameter(description = "로그인 회원 번호")
            Long sessionId,

            @PathVariable
            @Parameter(description = "페이징 번호")
            long page,

            @RequestParam(required = false)
            String sort
    ){
        RequestSessionTarget requestSessionTarget = new RequestSessionTarget();
        requestSessionTarget.setMyMemberId(sessionId);
        requestSessionTarget.setTargetId(id);
        requestSessionTarget.setPage(page * 6);
        requestSessionTarget.setSort(sort == null ? "created_at DESC" : sort);

        return pageService.getSpotListByPage(requestSessionTarget);
    }
    @GetMapping("/locMap/{id}")
    @Tag(name = "locMap", description = "locMap API")
    @Operation(summary = "방문한 지역 횟수")
    public List<ResponseLocMap> locMap(
            @PathVariable(required = false)
            @Schema(nullable = true)
            @Parameter(description = "대상 회원 번호")
            Long id
    ){
        return pageService.getLocMap(id);
    }
}
