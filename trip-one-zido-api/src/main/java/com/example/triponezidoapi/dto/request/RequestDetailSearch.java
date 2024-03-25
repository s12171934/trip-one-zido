package com.example.triponezidoapi.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RequestDetailSearch {
    Long myMemberId;
    @Size(max = 300)
    String keyword;
    int locCategory;
    int category;
    String season;
    int startMonth;
    int endMonth;
    long page;
}
