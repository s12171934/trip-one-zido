package com.example.triponezidoapi.dto.response;


import lombok.Data;
import java.util.List;

@Data
public class ResponseCommunityList {
    Long TotalCount;
    List<ResponseCommunity> communityList;
}
