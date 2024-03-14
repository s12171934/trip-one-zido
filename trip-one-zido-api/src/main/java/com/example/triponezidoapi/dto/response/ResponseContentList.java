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

    public ResponseContentList() {
    }

    public ResponseContentList(Long id, String title, byte[] photo, int bookmarkCount, int goodCount, int grade, int myBookmark, int pin){
        this.id = id;
        this.title = title;
        this.photo = photo;
        this.bookmarkCount = bookmarkCount;
        this.goodCount = goodCount;
        this.grade = grade;
        this.myBookmark = myBookmark;
        this.pin = pin;
    }
}
