package com.example.voltvortex.Activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.voltvortex.R;

public class ZSActivity extends AppCompatActivity {

    int buildingId, floorId, roomId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zs_activity);

        buildingId = getIntent().getExtras().getInt("BUILDING_ID");
        floorId = getIntent().getExtras().getInt("FLOOR_ID");
        roomId = getIntent().getExtras().getInt("ROOM_ID");

    }
}
