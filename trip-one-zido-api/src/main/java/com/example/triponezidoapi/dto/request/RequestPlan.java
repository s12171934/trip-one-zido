package com.example.triponezidoapi.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class RequestPlan {
    Long id;

    @Size(max = 200, message = "제목은 200자 이하로 작성해주세요")
    String title;

    boolean isPublic;

    LocalDateTime startDate;
    LocalDateTime endDate;

    @NotBlank(message = "지역을 선택해주세요")
    String locCategory;

    @NotBlank(message = "계획/여행중/여행완료 중 여행 상태를 선택해주세요")
    String status;

    @NotEmpty(message = "일정에 대한 후기를 공유해주세요")
    @Size(max = 500, message = "후기 작성은 최대 500자 입니다")
    String review;

    @NotNull(message = "평점을 남겨주세요")
    int grade;

    @NotNull(message = "대표사진을 등록해주세요")
    byte[] profile;

    List<Long> spots;

    @Size(max = 20)
    List<Long> members;

    public RequestPlan(Long id, String title, boolean isPublic, LocalDateTime startDate, LocalDateTime endDate, String locCategory,
                       String status, String review, int grade, byte[] profile, List<Long> spots, List<Long> members){
        this.id = id;
        this.title = title;
        this.isPublic = isPublic;
        this.startDate = startDate;
        this.endDate = endDate;
        this.locCategory = locCategory;
        this.status = status;
        this.review = review;
        this.grade = grade;
        this.profile = profile;
        this.spots = spots;
        this.members = members;
    }
}
