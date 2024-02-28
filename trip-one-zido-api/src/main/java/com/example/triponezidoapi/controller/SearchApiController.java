package com.example.triponezidoapi.controller;

import com.example.triponezidoapi.dto.Plan;
import com.example.triponezidoapi.dto.ProfileMember;
import com.example.triponezidoapi.dto.RequestDetailSearch;
import com.example.triponezidoapi.dto.Spot;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/search")
public class SearchApiController {
    @GetMapping("/member/{keyword}")
    public ProfileMember searchMember(@PathVariable String keyword){
        return new ProfileMember();
    }
    @GetMapping("/plan/{keyword}")
    public Plan searchPlan(@PathVariable String keyword){
        return new Plan();
    }
    @GetMapping("/spot/{keyword}")
    public Spot searchSpot(@PathVariable String keyword){
        return new Spot();
    }
    @GetMapping("/detail/plan")
    public Plan searchPlanDetail(@RequestBody RequestDetailSearch detail){
        return new Plan();
    }
    @GetMapping("/detail/spot")
    public Spot searchSpotDetail(@RequestBody RequestDetailSearch detail){
        return new Spot();
    }
}
