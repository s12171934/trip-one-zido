package com.example.triponezidoapi.controller;

import com.example.triponezidoapi.dto.Comment;
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
    public List<Comment> showComment(@PathVariable Long id){
        return commentMapper.getComment(id);
    }
    @PostMapping("/")
    public void addComment(@RequestBody Comment comment, Long id){
        comment.setMemberId(id);
        commentMapper.addComment(comment);
    }
    @PutMapping("/{id}")
    public void updateComment(@PathVariable Long id, @RequestBody Comment comment){
        comment.setId(id);
        commentMapper.updateComment(comment);
    }
}
