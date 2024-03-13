package com.example.triponezidoapi.dto.response;

import lombok.Data;

@Data
public class ResponseTour {
    Long id;
    String title;
    String address;
    String locCategory;
    String info;
    byte[] photo;
    int bookmarkCount;
    int myBookmark;
    long nextId;
    long prevId;

    public ResponseTour(Long id, String title, String address, String locCategory, String info, byte[] photo, int bookmarkCount, int myBookmark, long nextId, long prevId){
        this.id = id;
        this.title = title;
        this.address = address;
        this.locCategory = locCategory;
        this.info = info;
        this.photo = photo;
        this.bookmarkCount = bookmarkCount;
        this.myBookmark = myBookmark;
        this.nextId = nextId;
        this.prevId = prevId;
    }
}
