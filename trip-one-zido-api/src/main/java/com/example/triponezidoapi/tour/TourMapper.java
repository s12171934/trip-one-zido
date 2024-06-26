package com.example.triponezidoapi.tour;

import com.example.triponezidoapi.dto.request.*;
import com.example.triponezidoapi.dto.response.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TourMapper {
    //insert
    void addTour(RequestTour requestTour);

    //select
    ResponseTour getTour(RequestSessionTarget requestSessionTarget);
    List<ResponseTour> getTourList(RequestTourList requestTourList);
    int getTourListCount(RequestTourList requestTourList);
    Long getNextId(Long id);
    Long getPrevId(Long id);
}
