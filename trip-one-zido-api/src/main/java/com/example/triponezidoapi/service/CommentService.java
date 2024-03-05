package com.example.triponezidoapi.service;

import com.example.triponezidoapi.dto.request.RequestComment;
import com.example.triponezidoapi.dto.response.ResponseComment;
import com.example.triponezidoapi.mappers.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    CommentMapper commentMapper;

    //insert
    public void addComment(long sessionId, RequestComment requestComment){
        requestComment.setId(sessionId);
        commentMapper.addComment(requestComment);
    }

    //select
    public List<ResponseComment> getComment(long id){
        List<ResponseComment> responseComments =  commentMapper.getComment(id);
        return responseComments;
    }

    //update
    public void updateComment(long id, RequestComment requestComment){
        requestComment.setId(id);
        commentMapper.updateComment(requestComment);
    }

}
