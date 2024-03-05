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

    public ResponseSpotDetail spotDetail(long id, long sessionId){
        RequestSessionTarget requestSessionTarget = new RequestSessionTarget();
        requestSessionTarget.setTargetId(id);
        requestSessionTarget.setMyMemberId(sessionId);
        return spotMapper.getSpot(requestSessionTarget);
    }

    public void addSpot(RequestSpot requestSpot, long sessionId){
        requestSpot.setProfile(sessionId);
        spotMapper.addSpot(requestSpot);
    }

    public void updateSpot(long id, RequestSpot requestSpot){
        requestSpot.setId(id);
        spotMapper.updateSpot(requestSpot);
    }

    public void deleteSpot(long id){
        contentMapper.deleteContent(id);
    }
}
