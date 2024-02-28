package com.example.triponezidoapi.mappers;

import com.example.triponezidoapi.dto.ProfileMember;
import com.example.triponezidoapi.dto.RequestDetailSearch;
import com.example.triponezidoapi.dto.RequestListInfo;
import com.example.triponezidoapi.dto.ResponseContentList;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SearchMapper {
    List<ProfileMember> searchMember(String keyword);
    List<ResponseContentList> searchSpot(RequestListInfo requestListInfo);
    List<ResponseContentList> searchPlan(RequestListInfo requestListInfo);
    List<ResponseContentList> detailSearchSpot(RequestDetailSearch requestDetailSearch);
    List<ResponseContentList> detailSearchPlan(RequestDetailSearch requestDetailSearch);

}
