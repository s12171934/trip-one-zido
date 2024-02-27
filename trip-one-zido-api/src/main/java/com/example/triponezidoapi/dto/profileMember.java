package com.example.triponezidoapi.dto;

import lombok.Data;

@Data
public class profileMember {
    long id;
    String loginId;
    byte[] profile;
}
