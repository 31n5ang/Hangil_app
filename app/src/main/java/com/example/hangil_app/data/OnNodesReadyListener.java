package com.example.hangil_app.data;

import com.example.hangil_app.data.api.dto.Node;

import java.util.List;

@FunctionalInterface
public interface OnNodesReadyListener {
    void onNodesReady(List<Node> nodes);
}
