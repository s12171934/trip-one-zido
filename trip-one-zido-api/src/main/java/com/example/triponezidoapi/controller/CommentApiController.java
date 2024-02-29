package com.example.triponezidoapi.controller;

import com.example.triponezidoapi.dto.Response.ResponseComment;
import com.example.triponezidoapi.mappers.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentApiController {
    @PostMapping("/")
    public void addComment(long id, @RequestBody RequestComment requestComment){

    }
    @PutMapping("/{id}")
    public void updateComment(@PathVariable long id, @RequestBody RequestComment requestComment){

    }
    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable long id){
    }
}
