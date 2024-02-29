package com.example.triponezidoapi.mappers;

import com.example.triponezidoapi.dto.beforeUse.RequestListInfo;
import com.example.triponezidoapi.dto.beforeUse.RequestOwner;
import com.example.triponezidoapi.dto.response.ResponseContentList;
import com.example.triponezidoapi.dto.response.ResponseTour;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TourMapper {
    List<ResponseContentList> getTourList(RequestListInfo requestListInfo);
    ResponseTour getTourById(RequestOwner requestOwner);
    void addTour(ResponseTour responseTour);
}
