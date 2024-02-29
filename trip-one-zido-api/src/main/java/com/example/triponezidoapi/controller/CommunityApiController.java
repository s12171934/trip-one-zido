package com.example.triponezidoapi.controller;

import com.example.triponezidoapi.dto.request.RequestCommunity;
import com.example.triponezidoapi.dto.request.RequestCommunitySearch;
import com.example.triponezidoapi.dto.response.ResponseCommunity;
import com.example.triponezidoapi.dto.response.ResponseCommunityDetail;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/community")
public class CommunityApiController {
    @GetMapping("/list/{page}")
    public List<ResponseCommunity> showCommunityAll(@PathVariable int page){
        return null;
    }
    @GetMapping("/{id}")
    public ResponseCommunityDetail showCommunityDetail(@PathVariable long id){
        return null;
    }
    @PostMapping("/")
    public void addCommunity(@RequestBody RequestCommunity requestCommunity, @SessionAttribute(name = "id") long id){
    }
    @PutMapping("/{id}")
    public void updateCommunity(@RequestBody RequestCommunity requestCommunity, @PathVariable long id){

    }
    @DeleteMapping("/{id}")
    public void deleteCommunity(@PathVariable long id){

    }
    @GetMapping("/search/{page}")
    public List<RequestCommunity> searchCommunity(@RequestBody RequestCommunitySearch requestCommunitySearch, @PathVariable int page){
        return null;
    }
    @PostMapping("/member/{id}")
    public void entryCommunity(@PathVariable long id, @SessionAttribute(name = "id") long sessionId){

    }
    @DeleteMapping("/member/{id}")
    public void departureCommunity(@PathVariable long id, @SessionAttribute(name = "id") long sessionId){

    }
}