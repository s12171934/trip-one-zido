package com.example.triponezidoapi.commnet;

import com.example.triponezidoapi.dto.request.RequestComment;
import com.example.triponezidoapi.dto.response.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    //insert
    void addComment(RequestComment requestComment);

    //select
    List<ResponseComment> getComment(Long id);

    //update
    void updateComment(RequestComment requestComment);
}
