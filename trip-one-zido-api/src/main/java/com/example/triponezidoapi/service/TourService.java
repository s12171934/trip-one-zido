package com.example.triponezidoapi.service;

import com.example.triponezidoapi.dto.request.RequestSessionTarget;
import com.example.triponezidoapi.dto.request.RequestTour;
import com.example.triponezidoapi.dto.response.ResponseTour;
import com.example.triponezidoapi.mappers.TourMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

;import java.util.List;

@Service
public class TourService {
    @Autowired
    TourMapper tourMapper;

    public void addTour(RequestTour requestTour) {
        tourMapper.addTour(requestTour);
    }

    //select
    public ResponseTour getTour(long id){
        RequestSessionTarget requestSessionTarget = new RequestSessionTarget();
        requestSessionTarget.setMyMemberId(id);
        return tourMapper.getTour(requestSessionTarget);
    }
    public List<RequestTour> getTourList(long sessionId, long page){
        RequestSessionTarget requestSessionTarget = new RequestSessionTarget();
        requestSessionTarget.setMyMemberId(sessionId);
        requestSessionTarget.setPage(page);
     return tourMapper.getTourList(requestSessionTarget);
    }
    public long getNextId(long id){
     return tourMapper.getNextId(id);
    }
    public long getPrevId(long id){
     return tourMapper.getPrevId(id);
    }
}
