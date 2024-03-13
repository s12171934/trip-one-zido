package com.example.triponezidoapi.dto.request;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RequestComment {
    long id;

    @Size(max = 300, message = "댓글은 최대 300자 이하이어야 합니다.")
    String comment;

    long memberId;
    long contentId;

    public RequestComment(long id, String comment, long memberId, long contentId) {
        this.id = id;
        this.comment = comment;
        this.memberId = memberId;
        this.contentId = contentId;
    }
}
