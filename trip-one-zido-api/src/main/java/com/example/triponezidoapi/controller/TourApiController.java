package com.example.triponezidoapi.controller;

import com.example.triponezidoapi.dto.response.*;
import com.example.triponezidoapi.dto.request.*;
import com.example.triponezidoapi.mappers.TourMapper;
import com.example.triponezidoapi.service.TourService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tour")
@Tag(name = "Content")
public class TourApiController {
    @Autowired
    TourService tourService;

    @GetMapping("/")
    @Operation(summary = "관광지 목록 조회")
    public List<RequestTour> showTourAll(
            @SessionAttribute(name="id")
            @Parameter(description = "로그인 회원 정보")
             long sessionId,
            @PathVariable
            @Parameter(description = "페이지 번호")
            long page
    ){
        return tourService.getTourList(sessionId, page);
    }
    @GetMapping("/{id}")
    @Operation(summary = "관광지 상세 조회")
    public ResponseTour showTourDetail(
            @PathVariable
            @Parameter(description = "관광지 게시물 번호")
            long id
    ){
        return tourService.getTour(id);
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
