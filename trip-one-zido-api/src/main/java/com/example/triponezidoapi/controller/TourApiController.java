package com.example.triponezidoapi.controller;

import com.example.triponezidoapi.dto.Tour;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tour")
public class TourApiController {
    @GetMapping("/")
    public List<ResponseTour> showTourAll(){
        return null;
    }
    @GetMapping("/{id}")
    public ResponseTour showTourDetail(@PathVariable long id){
        return null;
    }
    @PostMapping("/")
    public void addTour(@RequestBody RequestTour requestTour){

    }
}
