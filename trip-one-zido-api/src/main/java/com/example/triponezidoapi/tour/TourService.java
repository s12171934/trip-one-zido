package com.example.triponezidoapi.tour;

import com.example.triponezidoapi.dto.request.*;
import com.example.triponezidoapi.dto.response.*;
import com.example.triponezidoapi.content.ContentMapper;
import com.example.triponezidoapi.spot.SpotMapper;
import com.example.triponezidoapi.tour.TourMapper;
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

    //GET
    public ResponseTour getTour(RequestSessionTarget requestSessionTarget){
        Long id = requestSessionTarget.getTargetId();

        ResponseTour responseTour = tourMapper.getTour(requestSessionTarget);

        //관광 정보 상세페이지 관광정보 이전/다음페이지 정보
        if(tourMapper.getPrevId(id) == null){
            responseTour.setPrevId(0);
        } else {
            responseTour.setPrevId(tourMapper.getPrevId(id));
        }

        if(tourMapper.getNextId(id) == null){
            responseTour.setNextId(0);
        } else {
            responseTour.setNextId(tourMapper.getNextId(id));
        }
        return responseTour;
    }

    public ResponseTourList getTourList(RequestTourList requestTourList){
        long page = requestTourList.getPage();
        if(page != 0 ){
            page = page * 6;
            requestTourList.setPage(page);
        }

        ResponseTourList responseTourList = new ResponseTourList();
        responseTourList.setTourLists(tourMapper.getTourList(requestTourList));
        responseTourList.setTourListCount((tourMapper.getTourListCount(requestTourList)));
        return responseTourList;
    }

    public List<ResponseTour> getTourListPage(RequestTourList requestTourList){
        long page = requestTourList.getPage();
        if(page != 0 ){
            page = page * 6;
            requestTourList.setPage(page);
        }
        return tourMapper.getTourList(requestTourList);
    }

    //POST
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
