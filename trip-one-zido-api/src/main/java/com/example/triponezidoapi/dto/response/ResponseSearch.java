package com.example.triponezidoapi.dto.response;

import lombok.Data;
import java.util.List;

@Data
public class ResponseSearch {
    List<ResponseMember> memberList;
    List<ResponseContentList> planList;
    List<ResponseContentList> spotList;

    String keyword;
    Integer locCategory;
    Integer category;
    String season;

    int memberCount;
    int planCount;
    int spotCount;
}
