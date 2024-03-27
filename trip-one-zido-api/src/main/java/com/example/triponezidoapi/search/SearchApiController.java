package com.example.triponezidoapi.search;

import com.example.triponezidoapi.dto.request.*;
import com.example.triponezidoapi.dto.response.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/search")
@Tag(name = "Search", description = "Search API")
public class SearchApiController {
    @Autowired
    SearchService searchService;
    @Autowired
    RedisService redisService;

    @GetMapping("/{keyword}")
    @Operation(summary = "기본 검색")
    public ResponseSearch searchKeyword(
        @SessionAttribute(name="id")
        @Parameter(description = "로그인 회원 번호")
        Long sessionId,

        @PathVariable
        @Parameter(description = "검색어")
        String keyword
    ){
        RequestSearch requestSearch = new RequestSearch();
        requestSearch.setMyMemberId(sessionId);
        requestSearch.setKeyword(keyword);
        requestSearch.setPage(0);

        redisService.setValues(keyword);
        return searchService.searchByKeyword(requestSearch);
    }

    @GetMapping("/{keyword}/spot/{page}")
    @Operation(summary = "기본 검색 - 장소 게시글")
    public List<ResponseContentList> searchMoreSpotKeyword(
        @SessionAttribute(name="id")
        @Parameter(description = "로그인 회원 번호")
        Long sessionId,

        @PathVariable
        @Parameter(description = "검색어")
        String keyword,

        @PathVariable
        @Parameter(description = "페이지 번호")
        Long page
    ){
        RequestSearch requestSearch = new RequestSearch();
        requestSearch.setMyMemberId(sessionId);
        requestSearch.setKeyword(keyword);
        requestSearch.setPage(page);
        return searchService.moreSpotByKeyword(requestSearch);
    }

    @GetMapping("/{keyword}/plan/{page}")
    @Operation(summary = "기본 검색 - 일정 게시글")
    public List<ResponseContentList> searchMorePlanKeyword(
        @SessionAttribute(name="id")
        @Parameter(description = "로그인 회원 번호")
        Long sessionId,

        @PathVariable
        @Parameter(description = "검색어")
        String keyword,

        @PathVariable
        @Parameter(description = "페이지 번호")
        Long page
    ){
        RequestSearch requestSearch = new RequestSearch();
        requestSearch.setMyMemberId(sessionId);
        requestSearch.setKeyword(keyword);
        requestSearch.setPage(page);
        return searchService.morePlanByKeyword(requestSearch);
    }

    @GetMapping("/{keyword}/member/{page}")
    @Operation(summary = "기본 검색 - 계정")
    public List<ResponseMember> searchMoreMemberKeyword(
        @SessionAttribute(name="id")
        @Parameter(description = "로그인 회원 번호")
        Long sessionId,

        @PathVariable
        @Parameter(description = "검색어")
        String keyword,

        @PathVariable
        @Parameter(description = "페이지 번호")
        Long page
    ){
        RequestSearch requestSearch = new RequestSearch();
        requestSearch.setMyMemberId(sessionId);
        requestSearch.setKeyword(keyword);
        requestSearch.setPage(page);
        return searchService.moreMemberByKeyword(requestSearch);
    }

    @GetMapping("/top10")
    @Operation(summary = "인기 검색어")
    public List<Object> topTenList(){
        return redisService.getValues();
    }

    @PostMapping("")
    @Operation(summary = "상세 검색")
    public ResponseSearch searchDetail(
        @SessionAttribute(name="id")
        @Parameter(description = "로그인 회원 번호")
        Long sessionId,

        @RequestBody
        @Parameter(description = "상세 검색 조건")
        RequestDetailSearch detailSearch
    ){
        detailSearch.setMyMemberId(sessionId);
        detailSearch.setPage(0);
        redisService.setValues(detailSearch.getKeyword());
        return searchService.searchByDetail(detailSearch);
    }

    @PostMapping("/spot/{page}")
    @Operation(summary = "상세 검색 - 장소 게시글")
    public List<ResponseContentList> searchMoreSpotDetail(
        @SessionAttribute(name="id")
        @Parameter(description = "로그인 회원 번호")
        Long sessionId,

        @RequestBody
        @Parameter(description = "상세 검색 조건")
        RequestDetailSearch detailSearch,

        @PathVariable
        @Parameter(description = "페이지 번호")
        Long page
    ){
        detailSearch.setMyMemberId(sessionId);
        detailSearch.setPage(page);
        return searchService.moreSpotByDetail(detailSearch);
    }

    @PostMapping("/plan/{page}")
    @Operation(summary = "상세 검색 - 일정 게시글")
    public List<ResponseContentList> searchMorePlanDetail(
        @SessionAttribute(name="id")
        @Parameter(description = "로그인 회원 번호")
        Long sessionId,

        @RequestBody
        @Parameter(description = "상세 검색 조건")
        RequestDetailSearch detailSearch,

        @PathVariable
        @Parameter(description = "페이지 번호")
        Long page
    ){
        detailSearch.setMyMemberId(sessionId);
        detailSearch.setPage(page);
        return searchService.morePlanByDetail(detailSearch);
    }
}
