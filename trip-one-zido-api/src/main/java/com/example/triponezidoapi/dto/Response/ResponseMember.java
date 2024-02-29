package com.example.triponezidoapi.dto.Response;

import lombok.Data;

@Data
public class ResponseMember {
    long id;
    String loginId;
    String own;
    byte[] profile;
}
