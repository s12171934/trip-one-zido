package com.example.triponezidoapi.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RequestCommunity {
    Long id;

    @Size(max = 200, message = "제목은 최대 200자 이하이어야 합니다.")
    String title;

    @NotNull(message = "여행시작일은 필수 입니다.")
    @Future(message = "여행시작일은 미래의 날짜여야 합니다.")
    LocalDateTime startDate;
    @NotNull(message = "여행종료일은 필수 입니다.")
    @Future(message = "여행종료일은 미래의 날짜여야 합니다.")
    LocalDateTime endDate;

    @NotNull(message = "지역분류는 필수 입니다.")
    int locCategory;

    @NotEmpty(message = "내용은 필수 입니다.")
    @Size(max = 500, message = "내용은 최대 500자 이하이어야 합니다.")
    String notice;

    @NotNull(message = "모집인원은 필수 입니다.")
    @Positive(message = "모집인원은 0보다 커야 합니다.")
    int total;

    @NotNull(message = "모집마감일은 필수 입니다.")
    @Future(message = "모집마감일은 미래의 날짜여야 합니다.")
    LocalDateTime deadline;

    int status;
}
