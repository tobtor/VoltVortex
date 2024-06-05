package com.example.voltvortex.Activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.voltvortex.DataBaseHelper.MyDatabaseHelper;
import com.example.voltvortex.Models.FloorModel;
import com.example.voltvortex.Models.RoomModel;
import com.example.voltvortex.R;
import com.example.voltvortex.RecyclerViewAdapters.FloorRecyclerViewAdapter;
import java.util.List;

public class FloorAndRoomActivity extends AppCompatActivity {

    int buildingId;
    RecyclerView recyclerView;
    FloorRecyclerViewAdapter floorAdapter;
    MyDatabaseHelper myDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floor_and_room);

        buildingId = getIntent().getExtras().getInt("BUILDING_ID");
        myDatabaseHelper = new MyDatabaseHelper(this);

        recyclerView = findViewById(R.id.recyclerViewFloorAndRoom);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<FloorModel> floors = myDatabaseHelper.getFloors(buildingId);
        floorAdapter = new FloorRecyclerViewAdapter(this, floors, buildingId);
        recyclerView.setAdapter(floorAdapter);
    }
}
