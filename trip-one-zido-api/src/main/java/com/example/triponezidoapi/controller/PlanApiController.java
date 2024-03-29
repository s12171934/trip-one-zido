package com.example.triponezidoapi.controller;

import com.example.triponezidoapi.dto.request.*;
import com.example.triponezidoapi.dto.response.*;
import com.example.triponezidoapi.service.PlanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/plan")
@Tag(name = "Plan", description = "Plan API")
public class PlanApiController {
    @Autowired
    PlanService planService;

    @GetMapping("/{id}")
    @Operation(summary = "일정 게시물 세부 조회")
    public ResponsePlanDetail showPlanDetail(
            @PathVariable
            @Parameter(description = "일정 게시물 번호")
            Long id,

            @SessionAttribute(name = "id")
            @Parameter(description = "로그인 회원 정보")
            Long sessionId
    ){
        return planService.getPlan(id, sessionId);
    }
    @PostMapping("")
    @Operation(summary = "일정 게시물 등록")
    public void postPlan(
            @SessionAttribute(name = "id")
            @Parameter(description = "로그인 회원 정보")
            Long sessionId,

            @RequestBody
            @Parameter(description = "일정 게시물 정보")
            RequestPlan requestPlan
    ){
        planService.addPlan(sessionId, requestPlan);
    }
    @PutMapping("/{id}")
    @Operation(summary = "일정 게시물 수정")
    public void putPlan(
            @PathVariable
            @Parameter(description = "일정 게시물 번호")
            Long id,

            @RequestBody
            @Parameter(description = "일정 게시물 정보")
            RequestPlan requestPlan
    ){
        planService.updatePlan(id, requestPlan);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "일정 게시물 삭제")
    public void deletePlan(
            @PathVariable
            @Parameter(description = "일정 게시물 번호")
            Long id
    ){
        planService.deletePlan(id);
    }
}
