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
        //addContent
        RequestContent requestContent = new RequestContent();
        requestContent.setType("comment");
        //댓글은 제목이 없으므로 null
        requestContent.setTitle(null);
        contentMapper.addContent(requestContent);

        //addOwner
        RequestOwner requestOwner = new RequestOwner();
        requestOwner.setOwn("writer");
        requestOwner.setMemberId(sessionId);
        requestOwner.setContentId(requestComment.getContentId());
        contentMapper.addOwner(requestOwner);

        //addComment
        requestComment.setMemberId(sessionId);
        commentMapper.addComment(requestComment);
    }

    public void updateComment(Long id, RequestComment requestComment){
        requestComment.setId(id);
        commentMapper.updateComment(requestComment);
    }

    public void deleteComment(Long id){
        contentMapper.deleteContent(id);
    }
}
