package com.example.triponezidoapi.dto.response;

import lombok.Data;

import java.util.List;
@Data
public class ResponseBookmark {
    Long id;
    String loginId;
    int tourBookmarkCount;
    int planSpotBookMarkCount;
    List<ResponseTour> tourList;
    List<ResponseContentList> contentList;
}
