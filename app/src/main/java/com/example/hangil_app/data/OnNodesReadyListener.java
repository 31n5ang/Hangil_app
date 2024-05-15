package com.example.hangil_app.data;

import com.example.hangil_app.data.api.response.Node;

import java.util.List;

public interface OnNodesReadyListener {
    void onNodesReady(List<Node> nodes);
}
