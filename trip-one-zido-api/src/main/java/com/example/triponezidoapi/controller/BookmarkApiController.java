package com.example.triponezidoapi.controller;

import com.example.triponezidoapi.dto.response.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookmark")
public class BookmarkApiController {
    @GetMapping("/{id}")
    public ResponseBookmark showBookmarkAll(@PathVariable(required = false) long id,@SessionAttribute(name = "id") long sessionId){
        return null;
    }
    @PostMapping("/{id}")
    public void addBookmark(@PathVariable long id, @SessionAttribute(name = "id") long sessionId){

    }
    @DeleteMapping("/{id}")
    public void deleteBookmark(@PathVariable long id){

    }
}
