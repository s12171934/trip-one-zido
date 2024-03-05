package com.example.triponezidoapi.service;

import com.example.triponezidoapi.dto.request.RequestDetailSearch;
import com.example.triponezidoapi.dto.request.RequestSearch;
import com.example.triponezidoapi.dto.response.ResponseSearch;
import com.example.triponezidoapi.mappers.SearchMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchService {
    @Autowired
    SearchMapper searchMapper;

    public ResponseSearch searchByKeyword(long id, String keyword){
        RequestSearch requestSearch = new RequestSearch();
        requestSearch.setMyMemberId(id);
        requestSearch.setKeyword(keyword);

        ResponseSearch responseSearch = new ResponseSearch();
        responseSearch.setMemberList(searchMapper.searchMember(keyword));
        responseSearch.setPlanList(searchMapper.searchPlan(requestSearch));
        responseSearch.setSpotList(searchMapper.searchSpot(requestSearch));
        responseSearch.setKeyword(keyword);

        return responseSearch;
    }

    public ResponseSearch searchByDetail(RequestDetailSearch detailSearch){
        ResponseSearch responseSearch = new ResponseSearch();

        responseSearch.setPlanList(searchMapper.detailSearchPlan(detailSearch));
        responseSearch.setSpotList(searchMapper.detailSearchSpot(detailSearch));
        responseSearch.setKeyword(detailSearch.getKeyword());
        responseSearch.setStartDuration(detailSearch.getStartDate());
        responseSearch.setEndDuration(detailSearch.getEndDate());
        responseSearch.setLocCategory(detailSearch.getLocCategory());
        responseSearch.setCategory(detailSearch.getCategory());

        return responseSearch;
    }
}
