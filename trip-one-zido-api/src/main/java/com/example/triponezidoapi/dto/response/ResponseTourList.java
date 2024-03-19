package com.example.triponezidoapi.dto.response;

import lombok.Data;
import java.util.List;

@Data
public class ResponseTourList {
    List<ResponseTour> tourLists;
    int tourListCount;
}
