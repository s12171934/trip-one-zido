package com.example.triponezidoapi.controller;

import com.example.triponezidoapi.dto.*;
import com.example.triponezidoapi.dto.RequestSearch;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/community")
public class CommunityApiController {

    @GetMapping("/page={page}")
    public List<CommunityTable> showCommunityList(@PathVariable int page){
        return null;
    }
    @GetMapping("/{id}")
    public CommunityDetail showCommunityDetail(@PathVariable long id){
        return new CommunityDetail();
    }
    @GetMapping("/{id}/owner")
    public ProfileMember showOwner(@PathVariable long id){
        return new ProfileMember();
    }
    @PostMapping("/")
    public void addCommunity(@RequestBody CommunityDetail communityDetail, long id){
    }
    @PutMapping("/{id}")
    public void updateCommunity(@RequestBody CommunityDetail communityDetail, @PathVariable long id){

    }
    @DeleteMapping("/{id}")
    public void deleteCommunity(@PathVariable long id){

    }
    @GetMapping("/search/page={page}")
    public RequestCommunitySearch searchCommunity(@RequestBody CommunityTable communityTable, @PathVariable int page){
        return new RequestCommunitySearch();
    }
    @PutMapping("entry/{id1}&{id2}")
    public void entryCommunity(@PathVariable long id1, @PathVariable long id2){

    }
    @PutMapping("departure/{id1}&{id2}")
    public void departureCommunity(@PathVariable long id1, @PathVariable long id2){

    }
}