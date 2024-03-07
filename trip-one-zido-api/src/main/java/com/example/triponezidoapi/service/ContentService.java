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

    public void addPin(Long id, Long sessionId) {
        RequestContentMember requestContentMember = new RequestContentMember();
        requestContentMember.setContentId(id);
        requestContentMember.setMemberId(sessionId);
        contentMapper.addPin(requestContentMember);
    }
    public void deletePin(Long id, Long sessionId) {
        RequestContentMember requestContentMember = new RequestContentMember();
        requestContentMember.setContentId(id);
        requestContentMember.setMemberId(sessionId);
        contentMapper.deletePin(requestContentMember);
    }
    public boolean isGood(Long id, Long sessionId){
        RequestContentMember requestContentMember = new RequestContentMember();
        requestContentMember.setContentId(id);
        requestContentMember.setMemberId(sessionId);
        return contentMapper.isGood(requestContentMember);
    }
    public void addGood(Long id, Long sessionId, RequestGood requestGood) {
        requestGood.setMemberId(sessionId);
        requestGood.setContentId(id);
        contentMapper.addGood(requestGood);
    }
    public List<ResponseContentList> getRecentView(Long sessionId, long page) {
        RequestSessionTarget requestSessionTarget = new RequestSessionTarget();
        requestSessionTarget.setPage(page);
        requestSessionTarget.setMyMemberId(sessionId);
        return contentMapper.getRecentView(requestSessionTarget);
    }
}
