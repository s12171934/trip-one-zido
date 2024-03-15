package com.example.triponezidoapi.dto.response;

import lombok.Data;

@Data
public class ResponseContentList {
    Long id;
    String title;
    byte[] photo;
    int bookmarkCount;
    int goodCount;
    int grade;
    int myBookmark;
    int pin;
    String type;
}
