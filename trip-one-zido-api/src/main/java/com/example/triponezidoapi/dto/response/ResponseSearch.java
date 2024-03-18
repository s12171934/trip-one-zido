package com.example.triponezidoapi.dto.response;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class ResponseSearch {
    List<ResponseMember> memberList = new ArrayList<>();
    List<ResponseContentList> planList = new ArrayList<>();
    List<ResponseContentList> spotList = new ArrayList<>();
    //항상 0으로 세팅되므로 삭제
//    int userCount = memberList.size();
//    int planCount = planList.size();
//    int spotCount = spotList.size();

    String keyword;
    String locCategory;
    String category;
    int memberCount;
}
