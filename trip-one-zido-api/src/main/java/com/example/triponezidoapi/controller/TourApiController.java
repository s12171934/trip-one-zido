package com.example.triponezidoapi.controller;

import com.example.triponezidoapi.dto.Tour;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tour")
public class TourApiController {
    @GetMapping("/")
    public Tour showTourList(){
        return new Tour();
    }
    @GetMapping("/{id}")
    public Tour showTourDetail(@PathVariable long id){
        return new Tour();
    }
    @PostMapping("/")
    public void addTour(@RequestBody Tour tour){

    }
}
