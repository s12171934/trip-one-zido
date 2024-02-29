package com.example.triponezidoapi.controller;

import com.example.triponezidoapi.dto.ResponseContentList;
import com.example.triponezidoapi.dto.Tour;
import jakarta.validation.GroupSequence;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookmark")
public class BookmarkApiController {
    @GetMapping("/{id}")
    public ResponseBookmark showBookmarkAll(@PathVariable long id, long sessionId){
        return null;
    }
    @PostMapping("/{id}")
    public void addBookmark(@PathVariable long id, long sessionId){

    }
    @DeleteMapping("/{id}")
    public void deleteBookmark(@PathVariable long id){

    }
}
