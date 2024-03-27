package com.example.triponezidoapi.search;

import com.example.triponezidoapi.dto.request.*;
import com.example.triponezidoapi.dto.response.*;
import com.example.triponezidoapi.search.SearchMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {
    @Autowired
    SearchMapper searchMapper;

    // GET
    // 기본 검색
    public ResponseSearch searchByKeyword(RequestSearch requestSearch){
        ResponseSearch responseSearch = new ResponseSearch();
        //searchMember
        responseSearch.setMemberList(searchMapper.searchMember(requestSearch));
        //searchPlan
        responseSearch.setPlanList(searchMapper.searchPlan(requestSearch));
        //searchSpot
        responseSearch.setSpotList(searchMapper.searchSpot(requestSearch));

        //검색 결과의 카운트 처리
        responseSearch.setMemberCount(searchMapper.countMember(requestSearch.getKeyword()));
        responseSearch.setPlanCount(searchMapper.countPlan(requestSearch));
        responseSearch.setSpotCount(searchMapper.countSpot(requestSearch));

        responseSearch.setKeyword(requestSearch.getKeyword());

        return responseSearch;
    }
    public List<ResponseContentList> moreSpotByKeyword(RequestSearch requestSearch){
        long page = requestSearch.getPage();
        if(page != 0){
            page = page * 6;
            requestSearch.setPage(page);
        }
        return searchMapper.searchSpot(requestSearch);
    }
    public List<ResponseContentList> morePlanByKeyword(RequestSearch requestSearch){
        long page = requestSearch.getPage();
        if(page != 0){
            page = page * 6;
            requestSearch.setPage(page);
        }
        return searchMapper.searchPlan(requestSearch);
    }
    public List<ResponseMember> moreMemberByKeyword(RequestSearch requestSearch){
        long page = requestSearch.getPage();
        if(page != 0){
            page = page * 6;
            requestSearch.setPage(page);
        }
        return searchMapper.searchMember(requestSearch);
    }

    // POST
    // 상세 검색
    // 기간 검색(계절별 기간 지정)
    RequestDetailSearch setSeasonSearch(RequestDetailSearch detailSearch){
        if(detailSearch.getSeason() == null){
            detailSearch.setSeason("");
        }

        switch (detailSearch.getSeason()){
            // 봄 3~5
            case "spring":
                detailSearch.setStartMonth("3");
                detailSearch.setEndMonth("5");
                break;
            // 여름 6~8
            case "summer":
                detailSearch.setStartMonth("6");
                detailSearch.setEndMonth("8");
                break;
            // 가을 9~11
            case "fall":
                detailSearch.setStartMonth("9");
                detailSearch.setEndMonth("11");
                break;
            // 겨울 12~2
            case "winter":
                detailSearch.setStartMonth("1");
                detailSearch.setEndMonth("2");
                break;
            // 입력이 없을 때 기본 1~12
            default:
                detailSearch.setStartMonth("1");
                detailSearch.setEndMonth("12");
        }

        return detailSearch;
    }

    public ResponseSearch searchByDetail(RequestDetailSearch detailSearch){
        ResponseSearch responseSearch = new ResponseSearch();

        //detailSearchSpot
        responseSearch.setSpotList(searchMapper.detailSearchSpot(setSeasonSearch(detailSearch)));
        //detailSearchPlan
        responseSearch.setPlanList(searchMapper.detailSearchPlan(setSeasonSearch(detailSearch)));

        responseSearch.setKeyword(detailSearch.getKeyword());

        //검색 결과의 카운트 처리
        responseSearch.setPlanCount(searchMapper.countDetailPlan(setSeasonSearch(detailSearch)));
        responseSearch.setSpotCount(searchMapper.countDetailSpot(setSeasonSearch(detailSearch)));

        return responseSearch;
    }

    public List<ResponseContentList> moreSpotByDetail(RequestDetailSearch detailSearch){
        long page = detailSearch.getPage();
        if(page != 0){
            page = page * 6;
            detailSearch.setPage(page);
        }
        return searchMapper.detailSearchSpot(setSeasonSearch(detailSearch));
    }

    public List<ResponseContentList> morePlanByDetail(RequestDetailSearch detailSearch){
        long page = detailSearch.getPage();
        if(page != 0){
            page = page * 6;
            detailSearch.setPage(page);
        }
        return searchMapper.detailSearchPlan(setSeasonSearch(detailSearch));
    }
}
