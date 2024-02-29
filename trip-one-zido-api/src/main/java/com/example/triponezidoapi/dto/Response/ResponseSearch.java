package com.example.triponezidoapi.dto.Response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ResponseSearch {
    List<ResponseMember> memberList;
    List<ResponseContentList> planList;
    List<ResponseContentList> spotList;
    String keyword;
    LocalDateTime startDuration;
    LocalDateTime endDuration;
    String locCategory;
    String category;
}
