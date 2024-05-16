package com.example.hangil_app.data.api.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IndoorPath {
    private int totalDistance;
    private List<Integer> path;
}
