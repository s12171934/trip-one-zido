package com.example.triponezidoapi.dto.response;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ResponsePlanDetail {
    Long id;
    LocalDate startDate;
    LocalDate endDate;
    int locCategory;
    int status;
    String review;
    int grade;
    int viewCount;
    int goodCount;
    Boolean myGood;
    int bookmarkCount;
    int myBookmark;
    String title;
    boolean visibility;
    LocalDateTime createdAt;
    List<ResponseSpotPlan> spots;
    ResponseMember writer;
    List<ResponseMember> members;
    List<ResponseComment> comments;
    boolean isMine;

}
