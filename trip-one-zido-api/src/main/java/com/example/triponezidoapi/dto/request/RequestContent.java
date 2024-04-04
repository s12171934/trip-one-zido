package com.example.triponezidoapi.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class RequestContent {
    Long id;
    String type;
    String title;
    boolean visibility = true;
}
