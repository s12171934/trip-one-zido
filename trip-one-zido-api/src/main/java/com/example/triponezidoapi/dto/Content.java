package com.example.triponezidoapi.dto;

import lombok.Data;

@Data
public class Content {
    long id;
    String type;
    String title = "comment";
    boolean isPublic = true;
}
