package com.example.triponezidoapi.dto.response;

import lombok.Data;

@Data
public class ResponseMember {
    Long id;
    String loginId;
    String own;
    byte[] profile;
    boolean isFollow;

    public ResponseMember(){
    }

    public ResponseMember(Long id, String loginId, String own, byte[] profile, boolean isFollow){
        this.id = id;
        this.loginId = loginId;
        this.own = own;
        this.profile = profile;
        this.isFollow = isFollow;
    }
}
