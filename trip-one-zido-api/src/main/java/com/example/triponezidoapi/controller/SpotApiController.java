package com.example.triponezidoapi.controller;

import com.example.triponezidoapi.dto.Photo;
import com.example.triponezidoapi.dto.RequestOwner;
import com.example.triponezidoapi.dto.Spot;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/spot")
public class SpotApiController {
    @GetMapping("/{id}")
    public ResponseSpotDetail showSpotDetail(){
        return null;
    }
    @PostMapping("/")
    public void addSpot(@RequestBody RequestSpot requestSpot, long id){

    }
    @PutMapping("/")
    public void updateSpot(@RequestBody RequestSpot requestSpot){

    }
    @DeleteMapping("/{id}")
    public void deleteSpot(@PathVariable long id){

    }
}
