package com.example.triponezidoapi.dto;

import lombok.Data;

@Data
public class ProfileMember {
    long id;
    String loginId;
    String own;
    byte[] profile;
}
