package com.example.triponezidoapi.dto;

import lombok.Data;

@Data
public class ResponseContentList {
    long id;
    String title;
    byte[] photo;
    int bookmarkCount;
    int likeCount;
    int grade;
    int myBookmark;
}
