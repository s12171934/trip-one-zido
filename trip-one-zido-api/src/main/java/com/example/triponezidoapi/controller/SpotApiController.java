package com.example.triponezidoapi.controller;

import com.example.triponezidoapi.dto.Photo;
import com.example.triponezidoapi.dto.RequestOwner;
import com.example.triponezidoapi.dto.Spot;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/spot")
public class SpotApiController {
    @GetMapping("/M{id}")
    public Spot showSpotAll(){
        return new Spot();
    }
    @GetMapping("/C{id}")
    public Spot showSpotDetail(){
        return new Spot();
    }
    @PostMapping("/")
    public void addSpot(@RequestBody Spot spot){

    }
    @PutMapping("/")
    public void updateSpot(@RequestBody Spot spot){

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
    public void deleteSpot(@PathVariable long id){

    }
}
