package com.example.triponezidoapi.controller;

import com.example.triponezidoapi.dto.response.ResponseContentList;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/content")
public class ContentApiController {
    @PostMapping("/pin/{id}")
    public void addPin(long id){

    }
    @DeleteMapping("/pin/{id}")
    public void deletePin(@PathVariable long id){

    }
    @PostMapping("/like/{id}")
    public void addLike(@PathVariable long id, long sessionId){

    }
    @GetMapping("/recent-view")
    public List<ResponseContentList> showRecentView(long id){
        return null;
    }
}
