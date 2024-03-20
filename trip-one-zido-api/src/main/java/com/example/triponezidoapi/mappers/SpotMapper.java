package com.example.triponezidoapi.mappers;

import com.example.triponezidoapi.dto.request.*;
import com.example.triponezidoapi.dto.response.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SpotMapper {
    //insert
    void addSpot(RequestSpot requestSpot);
    void addPhoto(RequestPhoto requestPhoto);

    //select
    ResponseSpotDetail getSpot(RequestSessionTarget requestSessionTarget);
    List<ResponseContentList> getSpotList(RequestSessionTarget requestSessionTarget);
    List<ResponsePhoto> getPhoto(Long id);
    int getSpotListCount(Long memberId);

    //update
    void updateSpot(RequestSpot requestSpot);

    //delete
    void deletePhoto(Long id);
}
