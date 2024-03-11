package com.example.triponezidoapi.controller;

import com.example.triponezidoapi.dto.request.*;
import com.example.triponezidoapi.dto.response.*;
import com.example.triponezidoapi.service.SearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/search/{page}")
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
            String keyword,
            @PathVariable
            @Parameter(description = "페이지 번호")
            long page){
        return searchService.searchByKeyword(sessionId, keyword, page);
    }
    @PostMapping("/detail")
    @Operation(summary = "상세 검색")
    public ResponseSearch searchDetail(
            @SessionAttribute(name="id")
            @Parameter(description = "로그인 회원 번호")
            Long sessionId,

            @RequestBody
            @Parameter(description = "상세 검색 조건")
            RequestDetailSearch detailSearch,
            @PathVariable
            @Parameter(description = "페이지 번호")
            long page){
        return searchService.searchByDetail(sessionId,detailSearch, page);
    }
}
