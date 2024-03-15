package com.example.triponezidoapi.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class RequestSpot {
    long id;

    @Size(max = 200, message = "제목은 200자 이하로 작성해주세요")
    String title;

    boolean isPublic;

    LocalDateTime startDate;
    LocalDateTime startTime;
    LocalDateTime endDate;
    LocalDateTime endTime;

    @NotBlank(message = "카테고리를 선택해주세요")
    String category;

    @NotBlank(message = "지역을 선택해주세요")
    String locCategory;

    @NotEmpty(message = "주소를 입력하세요")
    String address;

    String address2;

    @NotEmpty(message = "장소에 대한 후기를 공유해주세요")
    @Size(max = 500, message = "후기 작성은 최대 500자 입니다")
    String review;

    @NotNull(message = "평점을 남겨주세요")
    int grade;

    @NotNull(message = "대표사진을 골라주세요")
    long profile;

    @Size(max = 10)
    List<byte[]> photos;

    @Size(max = 20)
    List<String> members;
}
