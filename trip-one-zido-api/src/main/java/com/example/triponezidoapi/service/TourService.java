package com.example.triponezidoapi.service;

import com.example.triponezidoapi.dto.request.*;
import com.example.triponezidoapi.dto.response.*;
import com.example.triponezidoapi.mappers.ContentMapper;
import com.example.triponezidoapi.mappers.TourMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

;import java.util.List;

@Service
public class TourService {
    @Autowired
    TourMapper tourMapper;
    @Autowired
    ContentMapper contentMapper;

    public List<RequestTour> getTourList(Long sessionId, long page){
        RequestSessionTarget requestSessionTarget = new RequestSessionTarget();
        requestSessionTarget.setPage(page);
        requestSessionTarget.setMyMemberId(sessionId);
        return tourMapper.getTourList(requestSessionTarget);
    }

    public ResponseTour getTour(Long sessionId, Long id){
        RequestSessionTarget requestSessionTarget = new RequestSessionTarget();
        requestSessionTarget.setTargetId(id);
        requestSessionTarget.setMyMemberId(sessionId);
        return tourMapper.getTour(requestSessionTarget);
    }

    public void addTour(RequestTour requestTour) {
        // addContent
        RequestContent requestContent = new RequestContent();
        requestContent.setType("tour");
        requestContent.setTitle(requestTour.getTitle());
        contentMapper.addContent(requestContent);

        // addTour
        tourMapper.addTour(requestTour);
    }

}
