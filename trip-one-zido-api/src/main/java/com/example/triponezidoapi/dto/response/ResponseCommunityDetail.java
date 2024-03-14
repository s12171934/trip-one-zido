package com.example.triponezidoapi.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ResponseCommunityDetail {
    Long id;
    LocalDateTime startDate;
    LocalDateTime endDate;
    String locCategory;
    String notice;
    int total;
    LocalDateTime deadline;
    int viewPoint;
    String status;
    String title;
    LocalDateTime createdAt;
    LocalDateTime modifiedAt;
    List<ResponseMember> members;
    boolean isMine;
    long nextId;
    long prevId;

    public ResponseCommunityDetail(Long id, LocalDateTime startDate, LocalDateTime endDate, String locCategory, String notice, int total, LocalDateTime deadline, int viewPoint, String status, String title, LocalDateTime createdAt, LocalDateTime modifiedAt, List<ResponseMember> members, boolean isMine, long nextId, long prevId) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.locCategory = locCategory;
        this.notice = notice;
        this.total = total;
        this.deadline = deadline;
        this.viewPoint = viewPoint;
        this.status = status;
        this.title = title;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.members = members;
        this.isMine = isMine;
        this.nextId = nextId;
        this.prevId = prevId;
    }
}
