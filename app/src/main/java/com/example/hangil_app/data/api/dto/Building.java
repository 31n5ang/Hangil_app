package com.example.hangil_app.data.api.dto;

import lombok.Data;

@Data
public class Building {
    private int id;
    private String name;
    private String description;
    private double latitude;
    private double longitude;
}
