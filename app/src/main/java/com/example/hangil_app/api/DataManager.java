package com.example.hangil_app.api;

import android.util.Log;

import com.example.hangil_app.api.response.Building;
import com.example.hangil_app.api.response.Buildings;
import com.example.hangil_app.api.response.Node;
import com.example.hangil_app.api.response.Nodes;
import com.example.hangil_app.system.Hangil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataManager {
    public static DataManager dataManager; // Singleton
    private final Retrofit retrofit;
    private final RetrofitService retrofitService;
    private List<Building> buildings = new ArrayList<>();
    private List<Node> nodes = new ArrayList<>();
    public final Map<Integer, Building> buildingByIdMap = new HashMap<>();
    private DataManager(String baseUrl) {
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitService = retrofit.create(RetrofitService.class);
    }

    public static synchronized DataManager getInstance(String baseUrl) {
        if (dataManager == null) {
            return dataManager = new DataManager(baseUrl);
        } else {
            return dataManager;
        }
    }

    public void requestGetNodes(int buildingId, NodeType nodeType,
                                OnNodesReadyListener callback) {
        if (!nodes.isEmpty()) {
            callback.onNodesReady(nodes);
        } else {
            Call<Nodes> call = retrofitService.getNodes(buildingId, nodeType.name());
            call.enqueue(new Callback<Nodes>() {
                @Override
                public void onResponse(Call<Nodes> call, Response<Nodes> response) {
                    if (response.isSuccessful()) {
                        nodes = response.body().getNodes();
                        callback.onNodesReady(nodes);
                    } else {
                        Log.e(Hangil.API, response.errorBody().toString());
                        callback.onNodesReady(nodes);
                    }
                }

                @Override
                public void onFailure(Call<Nodes> call, Throwable t) {
                    Log.e(Hangil.API, t.toString());
                    callback.onNodesReady(nodes);
                }
            });
        }
    }

    public void requestGetBuildings(OnBuildingsReadyListener callback) {
        if (!buildings.isEmpty()) {
            callback.onBuildingsReady(buildings);
        } else {
            Call<Buildings> call = retrofitService.getBuildings();
            call.enqueue(new Callback<Buildings>() {
                @Override
                public void onResponse(Call<Buildings> call, Response<Buildings> response) {
                    if (response.isSuccessful()) {
                        buildings = response.body().getBuildings();
                        for (Building building : buildings) {
                            buildingByIdMap.put(building.getId(), building);
                        }
                        callback.onBuildingsReady(buildings);
                    } else {
                        Log.e(Hangil.API, response.errorBody().toString());
                        callback.onBuildingsReady(buildings);
                    }
                }

                @Override
                public void onFailure(Call<Buildings> call, Throwable t) {
                    Log.e(Hangil.API, t.toString());
                    callback.onBuildingsReady(buildings);
                }
            });
        }
    }
}