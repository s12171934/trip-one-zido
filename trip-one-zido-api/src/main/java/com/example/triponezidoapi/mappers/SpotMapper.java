package com.example.triponezidoapi.mappers;

import com.example.triponezidoapi.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SpotMapper {
    List<ResponseContentList> getSpotList(RequestListInfo requestListInfo);
    Spot getSpot(RequestOwner requestOwner);
    void addSpot(Spot spot);
    void updateSpot(Spot spot);
    void addPhoto(Photo photo);
}
