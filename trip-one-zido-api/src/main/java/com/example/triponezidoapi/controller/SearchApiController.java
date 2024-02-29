package com.example.triponezidoapi.controller;

import com.example.triponezidoapi.dto.request.*;
import com.example.triponezidoapi.dto.response.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/search")
public class SearchApiController {
    @GetMapping("/{keyword}")
    public ResponseSearch searchKeyword(
            @PathVariable
            String keyword){
        return null;
    }
    @GetMapping("/detail")
    public ResponseSearch searchPlan(
            @RequestBody
            RequestDetailSearch detailSearch){
        return null;
    }
}
