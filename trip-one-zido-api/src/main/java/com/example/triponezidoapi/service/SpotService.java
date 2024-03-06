package com.example.triponezidoapi.service;

import com.example.triponezidoapi.dto.request.*;
import com.example.triponezidoapi.dto.response.*;
import com.example.triponezidoapi.mappers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpotService {
    @Autowired
    SpotMapper spotMapper;
    @Autowired
    ContentMapper contentMapper;

    public ResponseSpotDetail spotDetail(Long id, Long sessionId){
//        getSpot,getComment,getOwner

        RequestSessionTarget requestSessionTarget = new RequestSessionTarget();
        requestSessionTarget.setTargetId(id);
        requestSessionTarget.setMyMemberId(sessionId);
        return spotMapper.getSpot(requestSessionTarget);
    }

    public void addSpot(RequestSpot requestSpot, Long sessionId){
//        addContent,addSpot, addPhoto,addOwner

        requestSpot.setProfile(sessionId);
        spotMapper.addSpot(requestSpot);
    }

    public void updateSpot(Long id, RequestSpot requestSpot){
//        updateSpot,updatePublic,updateTitle,deleteOwner,addOwner
        requestSpot.setId(id);
        spotMapper.updateSpot(requestSpot);
    }

    public void deleteSpot(Long id){
        contentMapper.deleteContent(id);
    }
}
