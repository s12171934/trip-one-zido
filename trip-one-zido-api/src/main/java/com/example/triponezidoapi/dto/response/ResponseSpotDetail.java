package com.example.triponezidoapi.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ResponseSpotDetail {
    long id;
    int category;
    LocalDateTime startDate;
    LocalDateTime endDate;
    int locCategory;
    String address;
    String address2;
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
    List<ResponsePhoto> photos;
    ResponseMember writer;
    List<ResponseMember> members;
    List<ResponseComment> comments;
    boolean isMine;
}
