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

    private RequestSearch setRequsetSearch(Long id, String keyword, long page){
        RequestSearch requestSearch = new RequestSearch();
        requestSearch.setMyMemberId(id);
        requestSearch.setKeyword(keyword);
        requestSearch.setPage(page);

        return requestSearch;
    }

    public ResponseSearch searchByKeyword(Long id, String keyword, long page){
        RequestSearch requestSearch = setRequsetSearch(id,keyword,page);

        ResponseSearch responseSearch = new ResponseSearch();
        //searchMember
        responseSearch.setMemberList(searchMapper.searchMember(requestSearch));
        //searchPlan
        responseSearch.setPlanList(searchMapper.searchPlan(requestSearch));
        //searchSpot
        responseSearch.setSpotList(searchMapper.searchSpot(requestSearch));

        //검색 결과의 카운트 처리
        responseSearch.setMemberCount(searchMapper.countMember(keyword));
        responseSearch.setPlanCount(searchMapper.countPlan(requestSearch));
        responseSearch.setSpotCount(searchMapper.countSpot(requestSearch));

        responseSearch.setKeyword(keyword);

        return responseSearch;
    }

    public List<ResponseContentList> moreSpotByKeyword(Long id, String keyword, long page){
        if(page != 0){
            page = page * 6;
        }
        RequestSearch requestSearch = setRequsetSearch(id,keyword,page);
        return searchMapper.searchSpot(requestSearch);
    }

    public List<ResponseContentList> morePlanByKeyword(Long id, String keyword, long page){
        if(page != 0){
            page = page * 6;
        }
        RequestSearch requestSearch = setRequsetSearch(id,keyword,page);
        return searchMapper.searchPlan(requestSearch);
    }

    public List<ResponseMember> moreMemberByKeyword(Long id, String keyword, long page){
        if(page != 0){
            page = page * 6;
        }
        RequestSearch requestSearch = setRequsetSearch(id,keyword,page);
        return searchMapper.searchMember(requestSearch);
    }

    RequestDetailSearch setDetailSearch(Long id, RequestDetailSearch detailSearch, long page){
        //detailSearchPlan 및 detailSearchSpot 에 필요한 MyMemberId 값 추가
        detailSearch.setMyMemberId(id);
        detailSearch.setPage(page);

        if(detailSearch.getSeason() == null){
            detailSearch.setSeason("");
        }

        // 기간검색 시즌이라는 정해진 category로 오는 경우
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
            default:
                detailSearch.setStartMonth("1");
                detailSearch.setEndMonth("12");
        }

        return detailSearch;
    }

    public ResponseSearch searchByDetail(Long id, RequestDetailSearch detailSearch, long page){
        ResponseSearch responseSearch = new ResponseSearch();
        //기본검색 페이지에서 상세검색 버튼 누르는 경우 (값이 전부 비어있음)
//        if(detailSearch.getSeason() == null && detailSearch.getCategory() == 0 && detailSearch.getLocCategory() ==0 ){
//            detailSearch.setMyMemberId(id);
//            detailSearch.setPage(page);
//            detailSearch.setCategory(1);
//            detailSearch.setLocCategory(11);
//
//            if(detailSearch.getLocCategory() == 0){
//                detailSearch.setLocCategory(null);
//            }
//
//            if(detailSearch.getCategory() == 0){
//                detailSearch.setCategory(null);
//            }
//
//
//            responseSearch.setPlanList(searchMapper.detailSearchPlan(detailSearch));
//            responseSearch.setSpotList(searchMapper.detailSearchSpot(detailSearch));
//
//            responseSearch.setPlanCount(searchMapper.countDetailPlan(detailSearch));
//            responseSearch.setSpotCount(searchMapper.countDetailSpot(detailSearch));
//
//            responseSearch.setCategory(0);
//            responseSearch.setLocCategory(0);
//        } else {
//            if(detailSearch.getSeason() != null){
        detailSearch = setDetailSearch(id, detailSearch, page);
//               }

        //detailSearchSpot
        responseSearch.setSpotList(searchMapper.detailSearchSpot(detailSearch));
        //detailSearchPlan
        responseSearch.setPlanList(searchMapper.detailSearchPlan(detailSearch));

        responseSearch.setKeyword(detailSearch.getKeyword());
//            if(detailSearch.getLocCategory() == null){
//                responseSearch.setLocCategory(0);
//            } else {
//                responseSearch.setLocCategory(detailSearch.getLocCategory());
//            }
//
//            if(detailSearch.getCategory() == null){
//                responseSearch.setCategory(0);
//            } else {
//                responseSearch.setCategory(detailSearch.getCategory());
//            }

        //검색 결과의 카운트 처리
        responseSearch.setPlanCount(searchMapper.countDetailPlan(detailSearch));
        responseSearch.setSpotCount(searchMapper.countDetailSpot(detailSearch));

//        }
        return responseSearch;
    }

    public List<ResponseContentList> moreSpotByDetail(Long id, RequestDetailSearch detailSearch, long page){
        if(page != 0){
            page = page * 6;
        }
        detailSearch = setDetailSearch(id,detailSearch,page);
        return searchMapper.detailSearchSpot(detailSearch);
    }

    public List<ResponseContentList> morePlanByDetail(Long id, RequestDetailSearch detailSearch, long page){
        if(page != 0){
            page = page * 6;
        }
        detailSearch = setDetailSearch(id,detailSearch,page);
        return searchMapper.detailSearchPlan(detailSearch);
    }
}
