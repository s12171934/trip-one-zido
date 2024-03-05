package com.example.triponezidoapi.service;

import com.example.triponezidoapi.dto.request.RequestPhoto;
import com.example.triponezidoapi.dto.request.RequestSessionTarget;
import com.example.triponezidoapi.dto.request.RequestSpot;
import com.example.triponezidoapi.dto.response.ResponseContentList;
import com.example.triponezidoapi.dto.response.ResponseSpotDetail;
import com.example.triponezidoapi.mappers.BookmarkMapper;
import com.example.triponezidoapi.mappers.SpotMapper;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpotService {

    @Autowired
    SpotMapper spotMapper;

    //insert
    public void addSpot(RequestSpot requestSpot, long sessionId){
        requestSpot.setProfile(sessionId);
        spotMapper.addSpot(requestSpot);
    }

    public void addPhoto(RequestPhoto requestPhoto){
        spotMapper.addPhoto(requestPhoto);
    }

    //select
    public ResponseSpotDetail spotDetail(long id, long sessionId){
        RequestSessionTarget requestSessionTarget = new RequestSessionTarget();
        requestSessionTarget.setTargetId(id);
        requestSessionTarget.setMyMemberId(sessionId);
        return spotMapper.getSpot(requestSessionTarget);
    }

    public List<ResponseContentList> getSpotList(RequestSessionTarget requestSessionTarget){
        List<ResponseContentList> spotList = spotMapper.getSpotList(requestSessionTarget);
        return spotList;
    }

    //update
    public void updateSpot(long id, RequestSpot requestSpot){
        requestSpot.setId(id);
        spotMapper.updateSpot(requestSpot);
    }
}
