package com.example.triponezidoapi.dto.response;

import lombok.Data;

@Data
public class ResponsePhoto {
    long contentId;
    byte[] photo;

    public ResponsePhoto() {
    }

    public ResponsePhoto(long contentId, byte[] photo) {
        this.contentId = contentId;
        this.photo = photo;
    }
}
