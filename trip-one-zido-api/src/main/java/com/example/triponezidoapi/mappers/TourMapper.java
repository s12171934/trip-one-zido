package com.example.triponezidoapi.mappers;

import com.example.triponezidoapi.dto.ResponseContentList;
import com.example.triponezidoapi.dto.Tour;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TourMapper {
    List<ResponseContentList> getTourList();
    Tour getTourById(long id);
    void addTour(Tour tour);
}
