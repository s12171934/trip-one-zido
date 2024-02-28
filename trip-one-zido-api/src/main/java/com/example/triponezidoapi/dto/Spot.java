package com.example.triponezidoapi.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
public class Spot {
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
    int bookmarkCount;
    int myBookmark;
    String title;
    String isPublic;
    LocalDateTime createdAt;
    ArrayList<byte[]> photos;
    ArrayList<ProfileMember> members;
    byte[] profile;
}
