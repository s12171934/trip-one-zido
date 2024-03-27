package com.example.triponezidoapi.commnet;

import com.example.triponezidoapi.commnet.CommentMapper;
import com.example.triponezidoapi.content.ContentMapper;
import com.example.triponezidoapi.dto.request.*;
import com.example.triponezidoapi.dto.response.ResponseComment;
import com.example.triponezidoapi.dto.response.ResponseMember;
import com.example.triponezidoapi.member.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    ContentMapper contentMapper;
    @Autowired
    MemberMapper memberMapper;

    public void deleteComment(Long id){
        contentMapper.deleteContent(id);
    }

    public List<ResponseComment> getComments(Long id){
        List<ResponseComment> responseCommentList = commentMapper.getComment(id);

        for(ResponseComment responseComment : responseCommentList){
            ResponseMember responseMember = memberMapper.getMemberProfile(responseComment.getMemberId());
            responseComment.setMember(responseMember);
            responseComment.setComments(getComments(responseComment.getId()));
        }

        return responseCommentList;
    }

    public void addComment(RequestComment requestComment){
        //addContent
        RequestContent requestContent = new RequestContent();
        requestContent.setType("comment");
        requestContent.setTitle(null);
        contentMapper.addContent(requestContent);
        Long generatedId = requestContent.getId();

        //addComment
        requestComment.setId(generatedId);
        commentMapper.addComment(requestComment);

        //addOwner
        RequestOwner requestOwner = new RequestOwner();
        requestOwner.setOwn("writer");
        requestOwner.setMemberId(requestComment.getMemberId());
        requestOwner.setContentId(requestComment.getId());
        contentMapper.addOwner(requestOwner);
    }

    public void updateComment(RequestComment requestComment){
        commentMapper.updateComment(requestComment);
    }
}
