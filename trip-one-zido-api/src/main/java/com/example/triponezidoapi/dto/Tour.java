package com.example.triponezidoapi.dto;

import lombok.Data;

@Data
public class Tour {
    long id;
    String title;
    String address;
    String locCategory;
    String info;
    byte[] photo;
    int bookmarkCount;
    int myBookmark;
}
