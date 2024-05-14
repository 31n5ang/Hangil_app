package com.example.hangil_app.api;

import com.example.hangil_app.api.response.Node;

import java.util.List;

public interface OnNodesReadyListener {
    void onNodesReady(List<Node> nodes);
}
