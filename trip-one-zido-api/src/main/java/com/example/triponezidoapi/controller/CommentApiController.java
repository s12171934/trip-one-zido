package com.example.triponezidoapi.controller;

import com.example.triponezidoapi.dto.Response.ResponseComment;
import com.example.triponezidoapi.mappers.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentApiController {
    @Autowired
    CommentMapper commentMapper;

    @GetMapping("/{id}")
    public List<ResponseComment> showComment(@PathVariable long id){
        return commentMapper.getComment(id);
    }
    @PostMapping("/")
    public void addComment(@RequestBody ResponseComment responseComment, long id){
        responseComment.setMemberId(id);
        commentMapper.addComment(responseComment);
    }
    @PutMapping("/{id}")
    public void updateComment(@PathVariable long id, @RequestBody ResponseComment responseComment){
        responseComment.setId(id);
        commentMapper.updateComment(responseComment);
    }
}
