package com.example.hangil_app.data.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PathNode {
    private int id;
    private int number;
    private int floor;
    private String type;
}
