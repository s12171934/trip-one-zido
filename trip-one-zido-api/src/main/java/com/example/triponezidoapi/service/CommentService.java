package com.example.triponezidoapi.service;

import com.example.triponezidoapi.dto.request.*;
import com.example.triponezidoapi.mappers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    ContentMapper contentMapper;

    public void addComment(long sessionId, RequestComment requestComment){
        requestComment.setId(sessionId);
        commentMapper.addComment(requestComment);
    }

    public void updateComment(long id, RequestComment requestComment){
        requestComment.setId(id);
        commentMapper.updateComment(requestComment);
    }

    public void deleteComment(long id){
        contentMapper.deleteContent(id);
    }
}
