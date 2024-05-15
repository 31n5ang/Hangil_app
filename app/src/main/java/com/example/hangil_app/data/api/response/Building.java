package com.example.hangil_app.data.api.response;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Building {
    private int id;
    private String name;
    private String description;
    @SerializedName("longitude")
    private double latitude;
    @SerializedName("latitude")
    private double longitude;
}
