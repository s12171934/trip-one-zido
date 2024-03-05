package com.example.triponezidoapi.service;

import com.example.triponezidoapi.dto.request.*;
import com.example.triponezidoapi.dto.response.ResponseContentList;
import com.example.triponezidoapi.mappers.ContentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentService {
    @Autowired
     ContentMapper contentMapper;

    public List<ResponseContentList> getRecentView(long sessionId, long page) {
        RequestSessionTarget requestSessionTarget = new RequestSessionTarget();
        requestSessionTarget.setMyMemberId(sessionId);
        requestSessionTarget.setPage(page);
        return contentMapper.getRecentView(requestSessionTarget);
    }

    public void addPin(long id, long sessionId) {
        RequestContentMember requestContentMember = new RequestContentMember();
        requestContentMember.setContentId(id);
        requestContentMember.setMemberId(sessionId);
        contentMapper.addPin(requestContentMember);
    }

    public void addGood(long id, long sessionId) {
        RequestGood requestGood = new RequestGood();
        requestGood.setMemberId(sessionId);
        requestGood.setContentId(id);
        contentMapper.addGood(requestGood);
    }

    public void deletePin(long id, long sessionId) {
        RequestContentMember requestContentMember = new RequestContentMember();
        requestContentMember.setMemberId(sessionId);
        requestContentMember.setContentId(id);
        contentMapper.deletePin(requestContentMember);
    }

    //삭제
    public void deleteContent(Long id){
        contentMapper.deleteContent(id);
    }
    public void addOwner(long id, long sessionId){
        RequestOwner requestOwner = new RequestOwner();
        requestOwner.setOwn("join");
        requestOwner.setContentId(id);
        requestOwner.setMemberId(sessionId);
        contentMapper.addOwner(requestOwner);
    }

    public void deleteOwner(long id, long sessionId){
        RequestContentMember requestContentMember = new RequestContentMember();
        requestContentMember.setContentId(id);
        requestContentMember.setMemberId(sessionId);
        contentMapper.deleteOwner(requestContentMember);
    }

}
