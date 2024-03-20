package com.example.triponezidoapi.dto.response;

import lombok.Data;

@Data
public class ResponseMember {
    Long id;
    String loginId;
    String own;
    byte[] profile;
    boolean isFollow;
    Long sessionId;
}
