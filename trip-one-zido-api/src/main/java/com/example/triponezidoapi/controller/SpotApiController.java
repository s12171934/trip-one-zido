package com.example.triponezidoapi.controller;

import com.example.triponezidoapi.dto.request.*;
import com.example.triponezidoapi.dto.response.*;
import com.example.triponezidoapi.service.SpotService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/spot")
@Tag(name = "Content")
public class SpotApiController {
    @GetMapping("/{id}")
    @Operation(summary = "장소 게시물 조회")
    public ResponseSpotDetail showSpotDetail(
            @PathVariable
            @Parameter(description = "장소 게시물 번호")
            long id,

            @SessionAttribute(name = "id")
            @Parameter(description = "로그인 회원 번호")
            long sessionId

    ){
        // 매퍼 getSpotList에 필요한 파라메타 타입에 맞추어, id, sessionId 를 요청객체로 생성
//        RequestSessionTarget reqSessionTarget = new RequestSessionTarget(0,id,sessionId);
//
//        //요청객체를 서비스로 전달 하여 서비스의 리턴값을 받음
//        SpotService spotService;
//
//        ResponseSpotDetail responseSpotDetail = spotService.spotDetail(reqSessionTarget);
//
//        //서비스에서 매퍼로 가고 매퍼의 리턴이 true라면
//
//        //그 값을 spotDetail로 리턴
//        return responseSpotDetail;
        return null;
    }
    @PostMapping("/")
    @Operation(summary = "장소 게시물 등록")
    public void addSpot(
            @RequestBody
            @Parameter(description = "장소 게시물 정보")
            RequestSpot requestSpot,

            @SessionAttribute(name = "id")
            @Parameter(description = "로그인 회원 번호")
            long sessionId
    ){

    }
    @PutMapping("/{id}")
    @Operation(summary = "장소 게시물 수정")
    public void updateSpot(
            @PathVariable
            @Parameter(description = "장소 게시물 번호")
            long id,

            @RequestBody
            @Parameter(description = "장소 게시물 정보")
            RequestSpot requestSpot
    ){

    }
    @DeleteMapping("/{id}")
    @Operation(summary = "장소 게시물 삭제")
    public void deleteSpot(
            @PathVariable
            @Parameter(description = "장소 게시물 번호")
            long id
    ){

    }
}
