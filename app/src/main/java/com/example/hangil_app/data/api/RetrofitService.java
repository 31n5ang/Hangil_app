package com.example.hangil_app.data.api;

import com.example.hangil_app.data.api.dto.BuildingSignals;
import com.example.hangil_app.data.api.dto.Buildings;
import com.example.hangil_app.data.api.dto.IndoorPath;
import com.example.hangil_app.data.api.dto.Nodes;
import com.example.hangil_app.data.api.dto.Position;
import com.example.hangil_app.data.api.dto.StartEndNode;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitService {
    @GET("v1/admin/buildings")
    Call<Buildings> getBuildings();

    @GET("v1/building/nodes/{buildingId}/{nodeType}")
    Call<Nodes> getNodes(
            @Path("buildingId") int buildingId,
            @Path("nodeType") String nodeType
    );

    @POST("v1/admin/node/position")
    Call<Position> getPosition(
            @Body BuildingSignals buildingSignals
    );

    @POST("v1/building/path")
    Call<IndoorPath> getIndoorPath(
            @Body StartEndNode startEndNode
    );
}
