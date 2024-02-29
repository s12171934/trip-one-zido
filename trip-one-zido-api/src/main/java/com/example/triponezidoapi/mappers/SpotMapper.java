package com.example.triponezidoapi.mappers;

import com.example.triponezidoapi.dto.beforeUse.RequestListInfo;
import com.example.triponezidoapi.dto.beforeUse.RequestOwner;
import com.example.triponezidoapi.dto.response.ResponseContentList;
import com.example.triponezidoapi.dto.response.ResponseSpotDetail;
import com.example.triponezidoapi.dto.beforeUse.Photo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SpotMapper {
    List<ResponseContentList> getSpotList(RequestListInfo requestListInfo);
    ResponseSpotDetail getSpot(RequestOwner requestOwner);
    void addSpot(ResponseSpotDetail responseSpotDetail);
    void updateSpot(ResponseSpotDetail responseSpotDetail);
    void addPhoto(Photo photo);
}
