package com.example.triponezidoapi.controller;

import com.example.triponezidoapi.dto.Photo;
import com.example.triponezidoapi.dto.Plan;
import com.example.triponezidoapi.dto.RequestOwner;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/plan")
public class PlanApiController {
    @GetMapping("/M{id}")
    public Plan showPlanAll(){
        return new Plan();
    }
    @GetMapping("/C{id}")
    public Plan showPlanDetail(){
        return new Plan();
    }
    @PostMapping("/")
    public void addPlan(@RequestBody Plan plan){

    }
    @PutMapping("/")
    public void updatePlan(@RequestBody Plan plan){

    }
    @PostMapping("/owner")
    public void addOwner(@RequestBody RequestOwner requestOwner){

    }
    @DeleteMapping("/owner/{id}")
    public void deleteOwner(@PathVariable long id){

    }
    @PostMapping("/photo")
    public void addPhoto(@RequestBody Photo photo){

    }
    @DeleteMapping("/photo/{id}")
    public void deletePhoto(@PathVariable long id){

    }
    @DeleteMapping("/C{id}")
    public void deletePlan(@PathVariable long id){

    }
}
