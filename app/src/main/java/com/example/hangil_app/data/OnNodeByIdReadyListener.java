package com.example.hangil_app.data;

import com.example.hangil_app.data.api.dto.NodeDetail;

public interface OnNodeByIdReadyListener {
    void onNodeByIdReady(NodeDetail nodeDetail);
}
