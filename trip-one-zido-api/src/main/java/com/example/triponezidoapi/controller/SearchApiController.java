package com.example.triponezidoapi.controller;

import com.example.triponezidoapi.dto.Plan;
import com.example.triponezidoapi.dto.ProfileMember;
import com.example.triponezidoapi.dto.RequestDetailSearch;
import com.example.triponezidoapi.dto.Spot;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/search")
public class SearchApiController {
    @GetMapping("/{keyword}")
    public ResponseSearch searchKeyword(@PathVariable String keyword){
        return null;
    }
    @GetMapping("/detail")
    public ResponseSearchDetail searchPlan(@RequestBody RequestDetailSearch detailSearch){
        return null;
    }
}
