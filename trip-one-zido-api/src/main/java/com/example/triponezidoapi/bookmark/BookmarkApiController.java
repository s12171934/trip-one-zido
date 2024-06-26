package com.example.triponezidoapi.bookmark;

import com.example.triponezidoapi.dto.request.RequestContentMember;
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
@RequestMapping("/api/bookmark")
public class BookmarkApiController {
    @Autowired
    BookmarkService bookmarkService;

    @DeleteMapping("/{id}")
    @Operation(summary = "찜 삭제")
    @Tag(name = "Bookmark")
    public void deleteBookmark(
            @PathVariable
            @Parameter(description = "찜 삭제할 게시물 번호")
            Long id,

            @SessionAttribute(name = "id")
            @Parameter(description = "로그인 회원 번호")
            Long sessionId
    ){
        RequestContentMember requestContentMember = new RequestContentMember();
        requestContentMember.setContentId(id);
        requestContentMember.setMemberId(sessionId);
        bookmarkService.deleteBookmark(requestContentMember);
    }
    @GetMapping(value = {"/{id}" , "/"})
    @Operation(summary = "찜 목록 전체 조회")
    @Tag(name = "Bookmark", description = "Bookmark API")
    public ResponseBookmark showBookmarkAll(
            @PathVariable(required = false)
            @Schema(nullable = true)
            @Parameter(description = "찜 목록 소유 회원 번호")
            Long id,

            @SessionAttribute(name = "id")
            @Parameter(description = "로그인 회원 번호")
            Long sessionId
    ){
        //id가 null일때 세션정보를 이용한다
        if(id == null){
            id = sessionId;
        }
        RequestSessionTarget requestSessionTarget = new RequestSessionTarget();
        requestSessionTarget.setTargetId(id);
        requestSessionTarget.setMyMemberId(sessionId);
        requestSessionTarget.setPage(0);
        return bookmarkService.getAllBookmark(requestSessionTarget);
    }
    @GetMapping("/{id}/SpotPlan/{page}")
    @Tag(name = "More", description = "More API")
    @Operation(summary = "찜목록 일정 장소 더보기 조회")
    public List<ResponseContentList> showSpotPlanListByPage(
            @PathVariable
            @Parameter(description = "찜 목록 소유 회원 번호")
            Long id,

            @SessionAttribute(name = "id")
            @Parameter(description = "로그인 회원 번호")
            Long sessionId,

            @PathVariable
            @Parameter(description = "페이징 번호")
            long page

    ){
        RequestSessionTarget requestSessionTarget = new RequestSessionTarget();
        requestSessionTarget.setMyMemberId(sessionId);
        requestSessionTarget.setTargetId(id);
        if(page != 0){
            requestSessionTarget.setPage(page * 6);
        }
        return bookmarkService.getPlanSpotBookmark(requestSessionTarget);
    }
    @GetMapping("/{id}/tour/{page}")
    @Tag(name = "More")
    @Operation(summary = "찜목록 관광지 더보기 조회")
    public List<ResponseTour> showTourListByPage(
            @PathVariable
            @Parameter(description = "찜 목록 소유 회원 번호")
            Long id,

            @SessionAttribute(name = "id")
            @Parameter(description = "로그인 회원번호")
            Long sessionId,

            @PathVariable
            @Parameter(description = "페이징 번호")
            long page
    ){
        RequestSessionTarget requestSessionTarget = new RequestSessionTarget();
        requestSessionTarget.setMyMemberId(sessionId);
        requestSessionTarget.setTargetId(id);
        if(page != 0){
            requestSessionTarget.setPage(page * 6);
        }
        return bookmarkService.getTourBookmark(requestSessionTarget);
    }
    @PostMapping("/{id}")
    @Operation(summary = "찜 등록")
    @Tag(name = "Bookmark")
    public void postBookmark(
            @PathVariable
            @Parameter(description = "찜 등록할 게시물 번호")
            Long id,

            @SessionAttribute(name = "id")
            @Parameter(description = "로그인 회원 번호")
            Long sessionId
    ){
        RequestContentMember requestContentMember = new RequestContentMember();
        requestContentMember.setContentId(id);
        requestContentMember.setMemberId(sessionId);
        bookmarkService.addBookMark(requestContentMember);
    }
}
