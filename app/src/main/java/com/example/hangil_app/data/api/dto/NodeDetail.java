package com.example.hangil_app.data.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NodeDetail {
    private String building;
    private String name;
    private String type;
    private String description;
    private int number;
    private int floor;
}
