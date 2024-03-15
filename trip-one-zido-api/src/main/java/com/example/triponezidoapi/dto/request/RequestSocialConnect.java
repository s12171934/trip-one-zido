package com.example.triponezidoapi.dto.request;

import lombok.Data;

@Data
public class RequestSocialConnect {
    String SocialType;
    String SocialId;
    String LoginId;
}
