package com.example.triponezidoapi.controller;

import com.example.triponezidoapi.dto.request.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentApiController {
    @PostMapping("/")
    public void addComment(
            @SessionAttribute(name = "id")
            long id,

            @RequestBody
            RequestComment requestComment){

    }
    @PutMapping("/{id}")
    public void updateComment(
            @PathVariable
            long id,

            @RequestBody
            RequestComment requestComment){

    }
    @DeleteMapping("/{id}")
    public void deleteComment(
            @PathVariable
            long id){
    }
}
