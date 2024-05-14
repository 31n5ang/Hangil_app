package com.example.hangil_app.api;

import com.example.hangil_app.api.response.Buildings;
import com.example.hangil_app.api.response.Nodes;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitService {
    @GET("v1/admin/buildings")
    Call<Buildings> getBuildings();

    @GET("v1/building/nodes/{buildingId}/{nodeType}")
    Call<Nodes> getNodes(
            @Path("buildingId") int buildingId,
            @Path("nodeType") String nodeType
    );
}
