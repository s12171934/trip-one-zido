package com.example.triponezidoapi.controller;

import com.example.triponezidoapi.dto.response.*;
import com.example.triponezidoapi.dto.request.*;
import com.example.triponezidoapi.service.TourService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/api/tour")
@Tag(name = "Tour", description = "Tour API")
public class TourApiController {
    @Autowired
    TourService tourService;

    @GetMapping("/list/{loc}")
    @Operation(summary = "관광지 목록 조회")
    public List<ResponseTour> showTourAll(
            @SessionAttribute(name="id")
            @Parameter(description = "로그인 회원 정보")
            Long sessionId,
            @PathVariable("loc")
            @Parameter(description = "장소 카테고리")
            String loc
    ){
        return tourService.getTourList(sessionId, loc , 0L);
    }

    @GetMapping("/list/{loc}/{page}")
    @Operation(summary = "관광지 목록 더보기")
    public List<ResponseTour> showMoreTour(
            @SessionAttribute(name="id")
            @Parameter(description = "로그인 회원 정보")
            Long sessionId,
            @PathVariable("loc")
            @Parameter(description = "장소 카테고리")
            String loc,
            @PathVariable("page")
            @Parameter(description = "페이지 번호")
            Long page
    ){
        return tourService.getTourList(sessionId, loc, page);
    }
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
        return tourService.getTour(sessionId, id);
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
