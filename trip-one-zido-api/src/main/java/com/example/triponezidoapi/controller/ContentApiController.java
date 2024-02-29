package com.example.triponezidoapi.controller;

import com.example.triponezidoapi.dto.response.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/content")
public class ContentApiController {
    @PostMapping("/pin/{id}")
    public void addPin(@SessionAttribute(name = "id") long id){

    }
    @DeleteMapping("/pin/{id}")
    public void deletePin(@PathVariable long id){

    }
    @PostMapping("/like/{id}")
    public void addLike(@PathVariable long id, @SessionAttribute(name = "id") long sessionId){

    }
    @GetMapping("/recent-view")
    public List<ResponseContentList> showRecentView(@SessionAttribute(name = "id") long id){
        return null;
    }
}
