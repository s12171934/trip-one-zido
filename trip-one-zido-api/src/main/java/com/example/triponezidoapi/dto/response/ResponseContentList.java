package com.example.triponezidoapi.dto.response;

import lombok.Data;

@Data
public class ResponseContentList {
    Long id;
    String loginId;
    String title;
    byte[] photo;
    int bookmarkCount;
    int goodCount;
    int grade;
    boolean myBookmark;
    int pin;
    String type;
}
