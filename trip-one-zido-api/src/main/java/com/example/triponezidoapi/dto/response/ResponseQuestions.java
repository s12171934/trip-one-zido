package com.example.triponezidoapi.dto.response;

import lombok.Data;

@Data
public class ResponseQuestions {
    long id;
    String question;

    public ResponseQuestions(long id, String question) {
        this.id = id;
        this.question = question;
    }
}
