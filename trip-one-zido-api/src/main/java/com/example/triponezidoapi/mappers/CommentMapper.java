package com.example.triponezidoapi.mappers;

import com.example.triponezidoapi.dto.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    //insert
    void addComment(Comment comment);

    //select
    List<Comment> getComment(long id);

    //update
    void updateComment(Comment comment);
}
