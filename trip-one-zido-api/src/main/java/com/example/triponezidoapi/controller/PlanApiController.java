package com.example.triponezidoapi.controller;

import com.example.triponezidoapi.dto.request.*;
import com.example.triponezidoapi.dto.response.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/plan")
@Tag(name = "Content")
public class PlanApiController {
    @GetMapping("/{id}")
    @Operation(summary = "일정 게시물 세부 조회")
    public ResponsePlanDetail showPlanDetail(
            @PathVariable
            @Parameter(description = "일정 게시물 번호")
            long id,

            @SessionAttribute(name = "id")
            @Parameter(description = "로그인 회원 정보")
            long sessionId
    ){
        return null;
    }
    @PostMapping("/")
    @Operation(summary = "일정 게시물 등록")
    public void addPlan(
            @SessionAttribute(name = "id")
            @Parameter(description = "로그인 회원 정보")
            long sessionId,

            @RequestBody
            @Parameter(description = "일정 게시물 정보")
            RequestPlan requestPlan
    ){

    }
    @PutMapping("/{id}")
    @Operation(summary = "일정 게시물 수정")
    public void updatePlan(
            @PathVariable
            @Parameter(description = "일정 게시물 번호")
            long id,

            @RequestBody
            @Parameter(description = "일정 게시물 정보")
            RequestPlan requestPlan
    ){

    }
    @DeleteMapping("/{id}")
    @Operation(summary = "일정 게시물 삭제")
    public void deletePlan(
            @PathVariable
            @Parameter(description = "일정 게시물 번호")
            long id
    ){

    }
}
