package com.example.triponezidoapi.dto.response;

import lombok.Data;

@Data
public class ResponseContentList {
    long id;
    String title;
    byte[] photo;
    int bookmarkCount;
    int goodCount;
    int grade;
    int myBookmark;
    int pin;
}
