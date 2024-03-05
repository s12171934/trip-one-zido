package com.example.triponezidoapi.service;

import com.example.triponezidoapi.dto.request.RequestPhoto;
import com.example.triponezidoapi.dto.request.RequestSessionTarget;
import com.example.triponezidoapi.dto.request.RequestSpot;
import com.example.triponezidoapi.dto.response.ResponseContentList;
import com.example.triponezidoapi.dto.response.ResponseSpotDetail;
import com.example.triponezidoapi.mappers.BookmarkMapper;
import com.example.triponezidoapi.mappers.SpotMapper;
import org.apache.coyote.Response;

import java.util.List;

public class SpotService {

    //사용할 매퍼 지정
    SpotMapper spotMapper;

    //매퍼 insert
    public void addSpot(RequestSpot requestSpot){
        spotMapper.addSpot(requestSpot);
    }

    public void addPhoto(RequestPhoto requestPhoto){
        spotMapper.addPhoto(requestPhoto);
    }

    //매퍼 select
    public ResponseSpotDetail spotDetail(RequestSessionTarget requestSessionTarget){
        ResponseSpotDetail responseSpotDetail = spotMapper.getSpot(requestSessionTarget);
        return responseSpotDetail;
    }

    public List<ResponseContentList> getSpotList(RequestSessionTarget requestSessionTarget){
        List<ResponseContentList> spotList = spotMapper.getSpotList(requestSessionTarget);
        return spotList;
    }

    //매퍼 update
    public void updateSpot(RequestSpot requestSpot){
        spotMapper.updateSpot(requestSpot);
    }
}
