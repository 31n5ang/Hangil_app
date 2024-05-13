package com.example.hangil_app.tmap.api;

import com.example.hangil_app.tmap.api.response.Buildings;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitService {
    @GET("v1/admin/buildings")
    Call<Buildings> getBuildings();
}
