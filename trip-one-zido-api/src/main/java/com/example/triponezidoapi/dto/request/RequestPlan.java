package com.example.triponezidoapi.dto.request;

import com.example.triponezidoapi.dto.response.ResponseMember;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class RequestPlan {
    Long id;

    @Size(max = 200, message = "제목은 200자 이하로 작성해주세요")
    String title;

    boolean visibility;

    LocalDate startDate;
    LocalDate endDate;

    @NotNull(message = "지역을 선택해주세요")
    int locCategory;

    @NotNull(message = "계획/여행중/여행완료 중 여행 상태를 선택해주세요")
    int status;

    @NotEmpty(message = "일정에 대한 후기를 공유해주세요")
    @Size(max = 500, message = "후기 작성은 최대 500자 입니다")
    String review;

    @NotNull(message = "평점을 남겨주세요")
    int grade;

    @NotNull(message = "대표사진을 등록해주세요")
    byte[] profile;

    List<RequestSpot> spots;

    @Size(max = 20)
    List<ResponseMember> members;
}
