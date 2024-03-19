package com.example.triponezidoapi.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ResponsePlanDetail {
    Long id;
    LocalDateTime startDate;
    LocalDateTime endDate;
    String locCategory;
    String status;
    String review;
    int grade;
    int viewCount;
    int goodCount;
    boolean myGood;
    int bookmarkCount;
    int myBookmark;
    String title;
    boolean visibility;
    LocalDateTime createdAt;
    List<ResponseSpotPlan> spots;
    List<ResponseMember> members;
    List<ResponseComment> comments;
    boolean isMine;

}
