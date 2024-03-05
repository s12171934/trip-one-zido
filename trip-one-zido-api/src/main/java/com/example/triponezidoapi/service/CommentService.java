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
    static CommentMapper commentMapper;

    //insert
    public static void addComment(RequestComment requestComment){
        commentMapper.addComment(requestComment);
    }

    //select
    public static List<ResponseComment> getComment(long id){
        List<ResponseComment> responseComments =  commentMapper.getComment(id);
        return responseComments;
    }

    //update
    public static void updateComment(RequestComment requestComment){
        commentMapper.updateComment(requestComment);
    }

}
