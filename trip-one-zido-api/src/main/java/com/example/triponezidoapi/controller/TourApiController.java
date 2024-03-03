package com.example.triponezidoapi.controller;

import com.example.triponezidoapi.dto.response.*;
import com.example.triponezidoapi.dto.request.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tour")
@Tag(name = "Content")
public class TourApiController {
    @GetMapping("/")
    @Operation(summary = "관광지 목록 조회")
    public List<ResponseTour> showTourAll(){
        return null;
    }
    @GetMapping("/{id}")
    @Operation(summary = "관광지 상세 조회")
    public ResponseTour showTourDetail(
            @PathVariable
            @Parameter(description = "관광지 게시물 번호")
            long id){
        return null;
    }
    @PostMapping("/")
    @Operation(summary = "관광지 등록")
    public void addTour(
            @RequestBody
            @Parameter(description = "관광지 정보")
            RequestTour requestTour){

    }
}
