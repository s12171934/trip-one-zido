package com.example.triponezidoapi.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
public class Plan {
    long id;
    LocalDateTime startDate;
    LocalDateTime endDate;
    String locCategory;
    String status;
    String review;
    int grade;
    int viewCount;
    int likeCount;
    int bookmarkCount;
    String title;
    String isPublic;
    LocalDateTime createdAt;
    ArrayList<Spot> spots;
    ArrayList<ProfileMember> members;
    byte[] profile;
}
