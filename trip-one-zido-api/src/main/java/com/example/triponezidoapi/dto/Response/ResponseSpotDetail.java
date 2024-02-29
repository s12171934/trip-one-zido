package com.example.triponezidoapi.dto.Response;

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
    String isPublic;
    LocalDateTime createdAt;
    byte[] profile;
    List<byte[]> photos;
    List<ResponseMember> members;
    List<ResponseComment> responseComments;

}