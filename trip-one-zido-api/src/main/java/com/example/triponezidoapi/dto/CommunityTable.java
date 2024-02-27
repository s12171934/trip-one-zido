package com.example.triponezidoapi.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class CommunityTable {
    long id;
    String title;
    String writer;
    LocalDateTime deadline;
    int viewPoint;
    int total;
    int participantsCount;
    String status;
}
