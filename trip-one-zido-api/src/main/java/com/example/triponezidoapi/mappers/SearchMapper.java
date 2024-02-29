package com.example.triponezidoapi.mappers;

import com.example.triponezidoapi.dto.response.ResponseMember;
import com.example.triponezidoapi.dto.request.RequestDetailSearch;
import com.example.triponezidoapi.dto.beforeUse.RequestListInfo;
import com.example.triponezidoapi.dto.response.ResponseContentList;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SearchMapper {
    List<ResponseMember> searchMember(String keyword);
    List<ResponseContentList> searchSpot(RequestListInfo requestListInfo);
    List<ResponseContentList> searchPlan(RequestListInfo requestListInfo);
    List<ResponseContentList> detailSearchSpot(RequestDetailSearch requestDetailSearch);
    List<ResponseContentList> detailSearchPlan(RequestDetailSearch requestDetailSearch);

}
