package com.example.triponezidoapi.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ResponseCommunity {
    Long id;
    String title;
    String writer;
    LocalDateTime deadline;
    int viewPoint;
    int total;
    int withCount;
    String status;

    public ResponseCommunity(Long id, String title, String writer, LocalDateTime deadline, int viewPoint, int total, int withCount, String status) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.deadline = deadline;
        this.viewPoint = viewPoint;
        this.total = total;
        this.withCount = withCount;
        this.status = status;
    }
}
