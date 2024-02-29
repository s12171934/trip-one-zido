package com.example.triponezidoapi.controller;

import com.example.triponezidoapi.dto.request.*;
import com.example.triponezidoapi.dto.response.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/spot")
public class SpotApiController {
    @GetMapping("/{id}")
    public ResponseSpotDetail showSpotDetail(){
        return null;
    }
    @PostMapping("/")
    public void addSpot(@RequestBody RequestSpot requestSpot, @SessionAttribute(name = "id") long id){

    }
    @PutMapping("/")
    public void updateSpot(@RequestBody RequestSpot requestSpot){

    }
    @DeleteMapping("/{id}")
    public void deleteSpot(@PathVariable long id){

    }
}
