package com.example.triponezidoapi.controller;

import com.example.triponezidoapi.dto.request.*;
import com.example.triponezidoapi.dto.response.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/search")
@Tag(name = "Search", description = "Search API")
public class SearchApiController {
    @GetMapping("/{keyword}")
    @Operation(summary = "기본 검색")
    public ResponseSearch searchKeyword(
            @PathVariable
            @Parameter(description = "검색어")
            String keyword){
        return null;
    }
    @GetMapping("/detail")
    @Operation(summary = "상세 검색")
    public ResponseSearch searchPlan(
            @RequestBody
            @Parameter(description = "상세 검색 조건")
            RequestDetailSearch detailSearch){
        return null;
    }
}
