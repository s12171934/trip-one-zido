package com.example.triponezidoapi.dto.response;

import lombok.Data;

@Data
public class ResponseMember {
    long id;
    String loginId;
    String own;
    byte[] profile;
    boolean isFollow;
}
