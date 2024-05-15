package com.example.hangil_app;

import static com.example.hangil_app.system.Hangil.BUILDING_ID;
import static com.example.hangil_app.system.Hangil.END_NODE;
import static com.example.hangil_app.system.Hangil.START_NODE;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class IndoorActivity extends AppCompatActivity {
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indoor);

        int buildingId = getIntent().getIntExtra(BUILDING_ID, 0);
        int startNode = getIntent().getIntExtra(START_NODE, -1);
        int endNode = getIntent().getIntExtra(END_NODE, -1);


        tv = findViewById(R.id.tv);

        tv.setText(String.format("%s건물, 출발:%s, 도착:%s", buildingId, startNode, endNode));
    }
}