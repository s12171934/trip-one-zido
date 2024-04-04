package com.example.triponezidoapi.tour;

import com.example.triponezidoapi.dto.request.*;
import com.example.triponezidoapi.dto.response.*;
import com.example.triponezidoapi.content.ContentMapper;
import com.example.triponezidoapi.spot.SpotMapper;
import com.example.triponezidoapi.util.LocationCode;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

;import java.util.ArrayList;
import java.util.List;

@Service
public class TourService {
    @Autowired
    TourMapper tourMapper;
    @Autowired
    ContentMapper contentMapper;
    @Autowired
    SpotMapper spotMapper;

    int apiPage = 1;

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
//        RequestPhoto requestPhoto = new RequestPhoto();
//        requestPhoto.setPhoto(requestTour.getPhoto());
//        requestPhoto.setContentId(generatedId);
//        spotMapper.addPhoto(requestPhoto);

        // addTour
        requestTour.setId(generatedId);
        tourMapper.addTour(requestTour);
    }

    public JSONObject getTourFromOpenApi(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> tours = restTemplate.exchange(
            "http://apis.data.go.kr/B551011/KorService1/areaBasedList1?serviceKey=zkPCGLLeYNp7DcQ1XOEYpbo80W8vTNv7cJs5z8VNP8CRbAbI7KMwWdAgBRmHJ/UVxEKnC2F6qi8jjnn9148NgA==&numOfRows=1000&pageNo=" + apiPage + "&contentTypeId=12&MobileOS=ETC&MobileApp=trip-one-zido&arrange=R&_type=json",
                HttpMethod.GET,
                null,
                String.class
        );
        apiPage++;
        JSONObject jsonObject = null;
        List<RequestTour> tourList = new ArrayList<>();
        try {
            JSONParser jsonParser = new JSONParser();
            jsonObject = (JSONObject)jsonParser.parse(tours.getBody());
            jsonObject = (JSONObject)jsonObject.get("response");
            jsonObject = (JSONObject)jsonObject.get("body");
            jsonObject = (JSONObject)jsonObject.get("items");
            JSONArray jsonArray = (JSONArray)jsonObject.get("item");
            for(Object obj : jsonArray){
                JSONObject tour = (JSONObject)obj;
                RequestTour requestTour = new RequestTour();
                requestTour.setAddress((String)tour.get("addr1"));
                requestTour.setLocCategory(LocationCode.getCode(requestTour.getAddress()));
                requestTour.setTitle((String)tour.get("title"));
                requestTour.setPhoto((String)tour.get("firstimage"));
                ResponseEntity<String> tourInfo = restTemplate.exchange(
                        "https://apis.data.go.kr/B551011/KorService1/detailCommon1?MobileOS=ETC&MobileApp=app&_type=json&contentId=" + tour.get("contentid") + "&overviewYN=Y&serviceKey=zkPCGLLeYNp7DcQ1XOEYpbo80W8vTNv7cJs5z8VNP8CRbAbI7KMwWdAgBRmHJ/UVxEKnC2F6qi8jjnn9148NgA==",
                        HttpMethod.GET,
                        null,
                        String.class
                );

                String info = "데이터 없음";

                try {
                    JSONObject jsonObject1 = (JSONObject)jsonParser.parse(tourInfo.getBody());
                    jsonObject1 = (JSONObject)jsonObject1.get("response");
                    jsonObject1 = (JSONObject)jsonObject1.get("body");
                    jsonObject1 = (JSONObject)jsonObject1.get("items");
                    JSONArray jsonArray1 = (JSONArray)jsonObject1.get("item");
                    info = (String)((JSONObject)jsonArray1.get(0)).get("overview");
                }
                catch (Exception e){

                }

                requestTour.setInfo(info);


                addTour(requestTour);
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


        return jsonObject;
    }

}
