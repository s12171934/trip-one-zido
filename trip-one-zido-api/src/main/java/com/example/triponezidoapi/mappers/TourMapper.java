package com.example.triponezidoapi.mappers;

import com.example.triponezidoapi.dto.RequestListInfo;
import com.example.triponezidoapi.dto.RequestOwner;
import com.example.triponezidoapi.dto.ResponseContentList;
import com.example.triponezidoapi.dto.Tour;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TourMapper {
    List<ResponseContentList> getTourList(RequestListInfo requestListInfo);
    Tour getTourById(RequestOwner requestOwner);
    void addTour(Tour tour);
}
