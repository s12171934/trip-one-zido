package com.example.triponezidoapi.dto.request;

import lombok.Data;

@Data
public class RequestTour {
    long id;
    String title;
    String address;
    String locCategory;
    String info;
    byte[] photo;

    public RequestTour(long id, String title, String address, String locCategory, String info, byte[] photo) {
        this.id = id;
        this.title = title;
        this.address = address;
        this.locCategory = locCategory;
        this.info = info;
        this.photo = photo;
    }
}
