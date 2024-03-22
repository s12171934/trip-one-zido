package com.example.triponezidoapi.dto.response;

import lombok.Data;

@Data
public class ResponseTour {
    Long id;
    String title;
    String address;
    int locCategory;
    String info;
    byte[] photo;
    int bookmarkCount;
    int myBookmark;
    long nextId;
    long prevId;
    String type;
}
