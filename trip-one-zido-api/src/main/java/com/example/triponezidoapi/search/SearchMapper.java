package com.example.triponezidoapi.search;

import com.example.triponezidoapi.dto.request.*;
import com.example.triponezidoapi.dto.response.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SearchMapper {
    //select
    List<ResponseMember> searchMember(RequestSearch requestSearch);
    List<ResponseContentList> searchSpot(RequestSearch requestSearch);
    List<ResponseContentList> searchPlan(RequestSearch requestSearch);
    List<ResponseContentList> detailSearchSpot(RequestDetailSearch requestDetailSearch);
    List<ResponseContentList> detailSearchPlan(RequestDetailSearch requestDetailSearch);

    int countMember(String keyword);
    int countSpot(RequestSearch requestSearch);
    int countPlan(RequestSearch requestSearch);

    int countDetailSpot(RequestDetailSearch requestDetailSearch);
    int countDetailPlan(RequestDetailSearch requestDetailSearch);
}
