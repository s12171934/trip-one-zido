package com.example.triponezidoapi.mappers;

import com.example.triponezidoapi.dto.Photo;
import com.example.triponezidoapi.dto.ResponseContentList;
import com.example.triponezidoapi.dto.Spot;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SpotMapper {
    List<ResponseContentList> getSpotList(long id);
    void addSpot(Spot spot);
    void updateSpot(Spot spot);
    void addPhoto(Photo photo);
    void deletePhoto(long id);
}
