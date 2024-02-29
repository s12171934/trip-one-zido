package com.example.triponezidoapi.dto.Request;

import lombok.Data;

@Data
public class RequestCommunitySearch {
    long page;
    String type;
    String keyword;
}
