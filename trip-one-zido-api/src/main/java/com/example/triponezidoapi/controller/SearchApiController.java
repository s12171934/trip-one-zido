package com.example.triponezidoapi.controller;

import com.example.triponezidoapi.dto.request.*;
import com.example.triponezidoapi.dto.response.*;
import com.example.triponezidoapi.service.SearchService;
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

    @GetMapping("/{keyword}")
    @Operation(summary = "기본 검색")
    public ResponseSearch searchKeyword(
            @SessionAttribute(name="id")
            @Parameter(description = "로그인 회원 번호")
            Long sessionId,
            @PathVariable
            @Parameter(description = "검색어")
            String keyword){
        return searchService.searchByKeyword(sessionId, keyword, 0);
    }

    @GetMapping("/{keyword}/spot/{page}")
    @Operation(summary = "기본 검색")
    public List<ResponseContentList> searchMoreSpotKeyword(
            @SessionAttribute(name="id")
            @Parameter(description = "로그인 회원 번호")
            Long sessionId,
            @PathVariable
            @Parameter(description = "검색어")
            String keyword,

            @PathVariable
            @Parameter(description = "페이지 번호")
            Long page){
        return searchService.moreSpotByKeyword(sessionId, keyword, page);
    }

    @GetMapping("/{keyword}/plan/{page}")
    @Operation(summary = "기본 검색")
    public List<ResponseContentList> searchMorePlanKeyword(
            @SessionAttribute(name="id")
            @Parameter(description = "로그인 회원 번호")
            Long sessionId,
            @PathVariable
            @Parameter(description = "검색어")
            String keyword,

            @PathVariable
            @Parameter(description = "페이지 번호")
            Long page){
        return searchService.morePlanByKeyword(sessionId, keyword, page);
    }

    @GetMapping("/{keyword}/member/{page}")
    @Operation(summary = "기본 검색")
    public List<ResponseMember> searchMoreMemberKeyword(
            @SessionAttribute(name="id")
            @Parameter(description = "로그인 회원 번호")
            Long sessionId,
            @PathVariable
            @Parameter(description = "검색어")
            String keyword,

            @PathVariable
            @Parameter(description = "페이지 번호")
            Long page){
        return searchService.moreMemberByKeyword(sessionId, keyword, page);
    }
    @PostMapping("")
    @Operation(summary = "상세 검색")
    public ResponseSearch searchDetail(
//            @SessionAttribute(name="id")
            @Parameter(description = "로그인 회원 번호")
            Long sessionId,

            @RequestBody
            @Parameter(description = "상세 검색 조건")
            RequestDetailSearch detailSearch){
        return searchService.searchByDetail(sessionId,detailSearch, 0);
    }

    @PostMapping("/spot/{page}")
    @Operation(summary = "상세 검색")
    public List<ResponseContentList> searchMoreSpotDetail(
//            @SessionAttribute(name="id")
            @Parameter(description = "로그인 회원 번호")
            Long sessionId,

            @RequestBody
            @Parameter(description = "상세 검색 조건")
            RequestDetailSearch detailSearch,

            @PathVariable
            @Parameter(description = "페이지 번호")
            Long page){
        return searchService.moreSpotByDetail(sessionId,detailSearch, page);
    }

    @PostMapping("/plan/{page}")
    @Operation(summary = "상세 검색")
    public List<ResponseContentList> searchMorePlanDetail(
//            @SessionAttribute(name="id")
            @Parameter(description = "로그인 회원 번호")
            Long sessionId,

            @RequestBody
            @Parameter(description = "상세 검색 조건")
            RequestDetailSearch detailSearch,

            @PathVariable
            @Parameter(description = "페이지 번호")
            Long page){
        return searchService.morePlanByDetail(sessionId,detailSearch, page);
    }
}
