package com.example.hangil_app.data;

import static com.example.hangil_app.system.Hangil.API;

import android.util.Log;

import com.example.hangil_app.data.api.RetrofitService;
import com.example.hangil_app.data.api.dto.Building;
import com.example.hangil_app.data.api.dto.BuildingSignals;
import com.example.hangil_app.data.api.dto.Buildings;
import com.example.hangil_app.data.api.dto.IndoorPath;
import com.example.hangil_app.data.api.dto.Node;
import com.example.hangil_app.data.api.dto.Nodes;
import com.example.hangil_app.data.api.dto.Position;
import com.example.hangil_app.data.api.dto.StartEndNode;

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
    public static final int MIN_BUILDING_INDEX = 1;
    public static final int BUILDING_COUNT = 1;
    public static DataManager dataManager; // Singleton
    private final Retrofit retrofit;
    private final RetrofitService retrofitService;
    private List<Building> buildings = new ArrayList<>();
    private List<Node> nodes = new ArrayList<>();
    public static final Map<Integer, Building> buildingByIdMap = new HashMap<>();
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
                        Log.e(API, response.errorBody().toString());
                        callback.onNodesReady(nodes);
                    }
                }

                @Override
                public void onFailure(Call<Nodes> call, Throwable t) {
                    Log.e(API, t.toString());
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
                        Log.e(API, response.errorBody().toString());
                        callback.onBuildingsReady(buildings);
                    }
                }

                @Override
                public void onFailure(Call<Buildings> call, Throwable t) {
                    Log.e(API, t.toString());
                    callback.onBuildingsReady(buildings);
                }
            });
        }
    }

    public void requestGetPosition(BuildingSignals buildingSignals,
                                   OnPositionReadyListener callback) {
        Call<Position> call = retrofitService.getPosition(buildingSignals);
        call.enqueue(new Callback<Position>() {
            @Override
            public void onResponse(Call<Position> call, Response<Position> response) {
                if (response.isSuccessful()) {
                    callback.onPositionReady(response.body());
                } else {
                    Log.e(API, response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<Position> call, Throwable t) {
                Log.e(API, t.toString());
            }
        });
    }

    public void requestGetIndoorPath(StartEndNode startEndNode,
                                     OnIndoorPathReadyListener callback) {
        Call<IndoorPath> call = retrofitService.getIndoorPath(startEndNode);
        call.enqueue(new Callback<IndoorPath>() {
            @Override
            public void onResponse(Call<IndoorPath> call, Response<IndoorPath> response) {
                if (response.isSuccessful()) {
                    callback.onIndoorPathReady(response.body());
                } else {
                    Log.e(API, response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<IndoorPath> call, Throwable t) {
                Log.e(API, t.toString());
            }
        });
    }
}