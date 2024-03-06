package com.example.triponezidoapi.dto.request;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class RequestSpot {
    long id;
    String category;
    LocalDateTime startDate;
    LocalDateTime endDate;
    String locCategory;
    String address;
    String review;
    int grade;
    String title;
    boolean isPublic;
    long profile;
    List<byte[]> photos;
    List<Long> members;
}
