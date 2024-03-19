package com.example.triponezidoapi.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ResponseSpotDetail {
    long id;
    String category;
    LocalDateTime startDate;
    LocalDateTime endDate;
    String locCategory;
    String address;
    String review;
    int grade;
    int viewCount;
    int goodCount;
    int myGood;
    int bookmarkCount;
    int myBookmark;
    String title;
    boolean visibility;
    LocalDateTime createdAt;
    List<ResponsePhoto> photos;
    List<ResponseMember> members;
    List<ResponseComment> comments;
    boolean isMine;
}
