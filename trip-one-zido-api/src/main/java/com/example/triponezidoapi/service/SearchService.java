package com.example.triponezidoapi.service;

import com.example.triponezidoapi.dto.request.*;
import com.example.triponezidoapi.dto.response.*;
import com.example.triponezidoapi.mappers.SearchMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchService {
    @Autowired
    SearchMapper searchMapper;

    public ResponseSearch searchByKeyword(Long id, String keyword, long page){
        RequestSearch requestSearch = new RequestSearch();
        requestSearch.setMyMemberId(id);
        requestSearch.setKeyword(keyword);
        requestSearch.setPage(page);

        ResponseSearch responseSearch = new ResponseSearch();
        //searchMember
        responseSearch.setMemberList(searchMapper.searchMember(requestSearch));
        //searchPlan
        responseSearch.setPlanList(searchMapper.searchPlan(requestSearch));
        //searchSpot
        responseSearch.setSpotList(searchMapper.searchSpot(requestSearch));
        responseSearch.setKeyword(keyword);

        return responseSearch;
    }

    public ResponseSearch searchByDetail(Long id, RequestDetailSearch detailSearch, long page){
        //detailSearchPlan 및 detailSearchSpot 에 필요한 MyMemberId 값 추가
        detailSearch.setMyMemberId(id);
        detailSearch.setPage(page);

        ResponseSearch responseSearch = new ResponseSearch();

        //detailSearchPlan
        responseSearch.setPlanList(searchMapper.detailSearchPlan(detailSearch));
        //detailSearchSpot
        responseSearch.setSpotList(searchMapper.detailSearchSpot(detailSearch));

        responseSearch.setKeyword(detailSearch.getKeyword());
        responseSearch.setStartDuration(detailSearch.getStartDate());
        responseSearch.setEndDuration(detailSearch.getEndDate());
        responseSearch.setLocCategory(detailSearch.getLocCategory());
        responseSearch.setCategory(detailSearch.getCategory());

        return responseSearch;
    }
}
