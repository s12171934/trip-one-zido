package com.example.triponezidoapi.spot;

import com.example.triponezidoapi.dto.request.*;
import com.example.triponezidoapi.dto.response.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/spot")
@Tag(name = "Spot", description = "Spot API")
public class SpotApiController {
    @Autowired
    SpotService spotService;

    @DeleteMapping("/{id}")
    @Operation(summary = "장소 게시물 삭제")
    public void deleteSpot(
            @PathVariable
            @Parameter(description = "장소 게시물 번호")
            Long id
    ){
        spotService.deleteSpot(id);
    }

    @GetMapping("/{id}")
    @Operation(summary = "장소 게시물 조회")
    public ResponseSpotDetail showSpotDetail(
        @PathVariable
        @Parameter(description = "장소 게시물 번호")
        Long id,

        @SessionAttribute(name = "id")
        @Parameter(description = "로그인 회원 번호")
        Long sessionId
    ){
        RequestSessionTarget requestSessionTarget = new RequestSessionTarget();
        requestSessionTarget.setTargetId(id);
        requestSessionTarget.setMyMemberId(sessionId);
        return spotService.spotDetail(requestSessionTarget);
    }

    @PostMapping("")
    @Operation(summary = "장소 게시물 등록")
    public void postSpot(
        @RequestBody
        @Parameter(description = "장소 게시물 정보")
        RequestSpot requestSpot,

        @SessionAttribute(name = "id")
        @Parameter(description = "로그인 회원 번호")
        Long sessionId
    ){
        spotService.addSpot(requestSpot, sessionId);
    }

    @PutMapping("/{id}")
    @Operation(summary = "장소 게시물 수정")
    public void putSpot(
        @PathVariable
        @Parameter(description = "장소 게시물 번호")
        Long id,

        @RequestBody
        @Parameter(description = "장소 게시물 정보")
        RequestSpot requestSpot
    ){
        requestSpot.setId(id);
        spotService.updateSpot(requestSpot);
    }

}
