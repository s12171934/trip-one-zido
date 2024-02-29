package com.example.triponezidoapi.controller;

import com.example.triponezidoapi.dto.Response.ResponseCommunityDetail;
import com.example.triponezidoapi.dto.Response.ResponseCommunity;
import com.example.triponezidoapi.dto.Response.ResponseMember;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/community")
public class CommunityApiController {

    @GetMapping("/page={page}")
    public List<ResponseCommunity> showCommunityList(@PathVariable int page){
        return null;
    }
    @GetMapping("/{id}")
    public ResponseCommunityDetail showCommunityDetail(@PathVariable long id){
        return new ResponseCommunityDetail();
    }
    @GetMapping("/{id}/owner")
    public ResponseMember showOwner(@PathVariable long id){
        return new ResponseMember();
    }
    @PostMapping("/")
    public void addCommunity(@RequestBody ResponseCommunityDetail responseCommunityDetail, long id){
    }
    @PutMapping("/{id}")
    public void updateCommunity(@RequestBody ResponseCommunityDetail responseCommunityDetail, @PathVariable long id){

    }
    @DeleteMapping("/{id}")
    public void deleteCommunity(@PathVariable long id){

    }
    @GetMapping("/search/page={page}")
    public List<ResponseCommunity> searchCommunity(@RequestBody ResponseCommunity responseCommunity, @PathVariable int page){
        return null;
    }
    @PutMapping("entry/{id1}&{id2}")
    public void entryCommunity(@PathVariable long id1, @PathVariable long id2){

    }
    @PutMapping("departure/{id1}&{id2}")
    public void departureCommunity(@PathVariable long id1, @PathVariable long id2){

    }
}