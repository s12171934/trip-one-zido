package com.example.triponezidoapi.dto.beforeUse;

import lombok.Data;

@Data
public class Content {
    long id;
    String type;
    String title = "comment";
    boolean isPublic = true;
}
