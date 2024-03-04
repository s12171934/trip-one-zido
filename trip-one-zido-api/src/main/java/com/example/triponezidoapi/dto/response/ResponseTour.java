package com.example.triponezidoapi.dto.response;

import lombok.Data;

@Data
public class ResponseTour {
    long id;
    String title;
    String address;
    String locCategory;
    String info;
    byte[] photo;
    int bookmarkCount;
    int myBookmark;
    long nextId;
    long prevId;
}
