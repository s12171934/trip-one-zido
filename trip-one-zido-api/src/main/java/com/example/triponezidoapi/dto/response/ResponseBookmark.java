package com.example.triponezidoapi.dto.response;

import lombok.Data;

import java.util.List;
@Data
public class ResponseBookmark {
    int TourBookmarkCount;
    int PlanSpotBookMarkCount;
    List<ResponseTour> tourList;
    List<ResponseContentList> contentList;
}
