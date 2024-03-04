package com.example.triponezidoapi.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ResponsePlanDetail {
    long id;
    LocalDateTime startDate;
    LocalDateTime endDate;
    String locCategory;
    String status;
    String review;
    int grade;
    int viewCount;
    int goodCount;
    int myGood;
    int bookmarkCount;
    int myBookmark;
    String title;
    String isPublic;
    LocalDateTime createdAt;
    List<ResponseSpotDetail> responseSpotDetails;
    List<ResponseMember> members;
    List<ResponseComment> comments;
    boolean isMine;
}
