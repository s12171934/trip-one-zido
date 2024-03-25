package com.example.triponezidoapi.dto.response;

import lombok.Data;
import java.util.List;

@Data
public class ResponseSearch {
    List<ResponseMember> memberList;
    List<ResponseContentList> planList;
    List<ResponseContentList> spotList;

    String keyword;
    int locCategory;
    int category;

    int memberCount;
    int planCount;
    int spotCount;
}
