package com.example.hangil_app.data.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Search {
    private int buildingId;
    private String nodeName;
}
