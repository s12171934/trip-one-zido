package com.example.triponezidoapi.service;

import com.example.triponezidoapi.dto.request.RequestContentMember;
import com.example.triponezidoapi.dto.request.RequestOwner;
import com.example.triponezidoapi.mappers.ContentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContentService {
    @Autowired
    ContentMapper contentMapper;

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
