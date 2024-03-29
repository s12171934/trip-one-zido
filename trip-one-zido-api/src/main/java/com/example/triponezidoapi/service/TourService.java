package com.example.triponezidoapi.service;

import com.example.triponezidoapi.dto.request.*;
import com.example.triponezidoapi.dto.response.*;
import com.example.triponezidoapi.mappers.ContentMapper;
import com.example.triponezidoapi.mappers.SpotMapper;
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
    @Autowired
    SpotMapper spotMapper;

    public ResponseTourList getTourList(Long sessionId, int loc, Long page){
        if(page != 0 ){
            page = page * 6;
        }
        //응답객체
        ResponseTourList responseTourList = new ResponseTourList();
        //요청
        RequestTourList requestTourList = new RequestTourList(page,loc,sessionId);
        responseTourList.setTourLists(tourMapper.getTourList(requestTourList));
        responseTourList.setTourListCount((tourMapper.getTourListCount(requestTourList)));

        return responseTourList;
    }
    public List<ResponseTour> getTourListPage(Long sessionId, int loc, Long page){
        if(page != 0 ){
            page = page * 6;
        }
        RequestTourList requestTourList = new RequestTourList(page,loc,sessionId);
        return tourMapper.getTourList(requestTourList);
    }

    public ResponseTour getTour(Long sessionId, Long id){
        RequestSessionTarget requestSessionTarget = new RequestSessionTarget();
        requestSessionTarget.setTargetId(id);
        requestSessionTarget.setMyMemberId(sessionId);
        ResponseTour responseTour = tourMapper.getTour(requestSessionTarget);

        //관광 정보 상세페이지 관광정보 이전/다음페이지 정보 - ParkJhh
        //만약 앞 관광정보가 존재하지 않는다면
        if(tourMapper.getPrevId(id) == null){
            responseTour.setPrevId(0);
        } else {
            responseTour.setPrevId(tourMapper.getPrevId(id));
        }
        //만약 다음 관광정보가 존재하지 않는다면
        if(tourMapper.getNextId(id) == null){
            responseTour.setNextId(0);
        } else {
            responseTour.setNextId(tourMapper.getNextId(id));
        }
        return responseTour;
    }

    public void addTour(RequestTour requestTour) {
        // addContent
        RequestContent requestContent = new RequestContent();
        requestContent.setType("tour");
        requestContent.setTitle(requestTour.getTitle());
        contentMapper.addContent(requestContent);
        //Content 테이블에 추가한 이후에 생성된 id를 가져옴
        Long generatedId = requestContent.getId();

        //addPhoto
        RequestPhoto requestPhoto = new RequestPhoto();
        requestPhoto.setPhoto(requestTour.getPhoto());
        requestPhoto.setContentId(generatedId);
        spotMapper.addPhoto(requestPhoto);

        // addTour
        requestTour.setId(generatedId);
        tourMapper.addTour(requestTour);
    }

}
