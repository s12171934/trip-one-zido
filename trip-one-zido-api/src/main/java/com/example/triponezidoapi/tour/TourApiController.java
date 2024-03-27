package com.example.triponezidoapi.tour;

import com.example.triponezidoapi.dto.response.*;
import com.example.triponezidoapi.dto.request.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tour")
@Tag(name = "Tour", description = "Tour API")
public class TourApiController {
    @Autowired
    TourService tourService;

    @GetMapping("/{id}")
    @Operation(summary = "관광지 상세 조회")
    public ResponseTour showTourDetail(
            @SessionAttribute(name="id")
            @Parameter(description = "로그인 회원 정보")
            Long sessionId,

            @PathVariable
            @Parameter(description = "관광지 게시물 번호")
            Long id
    ){
        RequestSessionTarget requestSessionTarget = new RequestSessionTarget();
        requestSessionTarget.setTargetId(id);
        requestSessionTarget.setMyMemberId(sessionId);
        return tourService.getTour(requestSessionTarget);
    }

    @GetMapping("/list/{loc}")
    @Operation(summary = "관광지 목록 조회")
    public ResponseTourList showTourAll(
        @SessionAttribute(name="id")
        @Parameter(description = "로그인 회원 정보")
        Long sessionId,

        @PathVariable("loc")
        @Parameter(description = "장소 카테고리")
        int loc
    ){
        RequestTourList requestTourList = new RequestTourList(0L, loc, sessionId);
        return tourService.getTourList(requestTourList);
    }

    @GetMapping("/list/{loc}/{page}")
    @Operation(summary = "관광지 목록 더보기")
    public List<ResponseTour> showMoreTour(
        @SessionAttribute(name="id")
        @Parameter(description = "로그인 회원 정보")
        Long sessionId,

        @PathVariable("loc")
        @Parameter(description = "장소 카테고리")
        int loc,

        @PathVariable("page")
        @Parameter(description = "페이지 번호")
        Long page
    ){
        RequestTourList requestTourList = new RequestTourList(page, loc, sessionId);
        return tourService.getTourListPage(requestTourList);
    }

    @PostMapping("/")
    @Operation(summary = "관광지 등록")
    public void postTour(
        @RequestBody
        @Parameter(description = "관광지 정보")
        RequestTour requestTour
    ){
        tourService.addTour(requestTour);
    }
}
