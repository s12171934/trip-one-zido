package com.example.triponezidoapi.controller;

import com.example.triponezidoapi.dto.request.RequestPlan;
import com.example.triponezidoapi.dto.response.ResponsePlanDetail;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/plan")
public class PlanApiController {
    @GetMapping("/{id}")
    public ResponsePlanDetail showPlanDetail(){
        return null;
    }
    @PostMapping("/")
    public void addPlan(@RequestBody RequestPlan requestPlan, long id){

    }
    @PutMapping("/{id}")
    public void updatePlan(@PathVariable long id, @RequestBody RequestPlan requestPlan){

    }
    @DeleteMapping("/{id}")
    public void deletePlan(@PathVariable long id){

    }
}
