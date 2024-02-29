package com.example.triponezidoapi.mappers;

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
    List<RequestTour> getTourList(RequestSessionTarget requestSessionTarget);
}
