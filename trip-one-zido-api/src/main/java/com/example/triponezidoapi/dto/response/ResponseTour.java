package com.example.triponezidoapi.dto.response;

import lombok.Data;

@Data
public class ResponseTour {
    Long id;
    String title;
    String address;
    int locCategory;
    String info;
    String photo;
    int bookmarkCount;
    int myBookmark;
    long nextId;
    long prevId;
    String type;
}
