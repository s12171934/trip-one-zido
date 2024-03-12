package com.example.triponezidoapi.dto.request;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RequestCommunitySearch {
    long page;
    String type;

    @Size(max = 300, message = "검색키워드는 최대 300자 이하이어야 합니다.")
    String keyword;
}
