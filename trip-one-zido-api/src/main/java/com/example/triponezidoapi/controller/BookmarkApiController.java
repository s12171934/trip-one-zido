package com.example.triponezidoapi.controller;

import com.example.triponezidoapi.dto.response.*;
import com.example.triponezidoapi.service.BookmarkService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookmark")
@Tag(name = "Bookmark", description = "Bookmark API")
public class BookmarkApiController {

    @Autowired
    BookmarkService bookmarkService;

    @GetMapping("/{id}")
    @Operation(summary = "찜 목록 전체 조회")
    public ResponseBookmark showBookmarkAll(
            @PathVariable(required = false)
            @Schema(nullable = true)
            @Parameter(description = "찜 목록 소유 회원 번호")
            long id,

            @SessionAttribute(name = "id")
            @Parameter(description = "로그인 회원 번호")
            long sessionId
    ){
        //id가 비어있다면 세션정보 이용
//        if(id < 0L){
//            id = sessionId;
//        }

        ResponseBookmark responseBookmark = new ResponseBookmark();
        responseBookmark.setTourBookmarkCount(bookmarkService.tourBookmarkCount(id));
        responseBookmark.setPlanSpotBookMarkCount(bookmarkService.planSpotBookmarkCount(id));
        responseBookmark.setTourList(bookmarkService.getTourBookmark(id,0));
        responseBookmark.setContentList(bookmarkService.getPlanSpotBookmark(id,0));
        return responseBookmark;
    }
    @GetMapping("/{id}/SpotPlan/{page}")
    @Tag(name = "Plan")
    @Operation(summary = "찜목록 일정 장소 더보기 조회")
    public List<ResponseContentList> showSpotPlanListByPage(
            @PathVariable
            @Parameter(description = "찜 목록 소유 회원 번호")
            long id,

            @PathVariable
            @Parameter(description = "페이징 번호")
            long page
    ){
        return bookmarkService.getPlanSpotBookmark(id,page);
    }
    @GetMapping("/{id}/tour/{page}")
    @Tag(name = "Plan")
    @Operation(summary = "찜목록 관광지 더보기 조회")
    public List<ResponseTour> showTourListByPage(
            @PathVariable
            @Parameter(description = "찜 목록 소유 회원 번호")
            long id,

            @PathVariable
            @Parameter(description = "페이징 번호")
            long page
    ){
        return bookmarkService.getTourBookmark(id,page);
    }
    @PostMapping("/{id}")
    @Operation(summary = "찜 등록")
    public void addBookmark(
            @PathVariable
            @Parameter(description = "찜 등록할 게시물 번호")
            long id,

            @SessionAttribute(name = "id")
            @Parameter(description = "로그인 회원 번호")
            long sessionId
    ){
        bookmarkService.addBookMark(id,sessionId);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "찜 삭제")
    public void deleteBookmark(
            @PathVariable
            @Parameter(description = "찜 삭제할 게시물 번호")
            long id,

            @SessionAttribute(name = "id")
            @Parameter(description = "로그인 회원 번호")
            long sessionId
    ){
        bookmarkService.deleteBookmark(id,sessionId);
    }
}
