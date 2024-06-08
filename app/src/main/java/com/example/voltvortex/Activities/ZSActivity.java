package com.example.voltvortex.Activities;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.voltvortex.DataBaseHelper.MyDatabaseHelper;
import com.example.voltvortex.Helpers.ZSPointDialogHelper;
import com.example.voltvortex.Models.ZSModel;
import com.example.voltvortex.R;
import com.example.voltvortex.RecyclerViewAdapters.ZSRecyclerViewAdapter;

import java.util.List;

public class ZSActivity extends AppCompatActivity {

    int buildingId;
    int floorId;
    int roomId;
    RecyclerView recyclerView;
    ZSRecyclerViewAdapter zsAdapter;
    MyDatabaseHelper myDatabaseHelper;
    Button buttonAddZSPoint, buttonUpFloor, buttonDownFloor, buttonPreviousRoom, buttonNextRoom;
    TextView textViewFloorRoom;
    float result = 0.7f;

    List<Integer> floors;
    List<Integer> rooms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zs);

        buildingId = getIntent().getExtras().getInt("BUILDING_ID");
        floorId = getIntent().getExtras().getInt("FLOOR_ID");
        roomId = getIntent().getExtras().getInt("ROOM_ID");

        myDatabaseHelper = new MyDatabaseHelper(this);

        buttonUpFloor = findViewById(R.id.buttonUpFloor);
        buttonDownFloor = findViewById(R.id.buttonDownFloor);
        buttonPreviousRoom = findViewById(R.id.buttonPreviousRoom);
        buttonNextRoom = findViewById(R.id.buttonNextRoom);
        buttonAddZSPoint = findViewById(R.id.butonAddZSPoint);
        textViewFloorRoom = findViewById(R.id.textViewFoorAndRoom);

        recyclerView = findViewById(R.id.recyclerViewZS);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        floors = myDatabaseHelper.getFloorsById(buildingId);
        rooms = myDatabaseHelper.getRooms(buildingId, floorId);

        List<ZSModel> zsPoints = myDatabaseHelper.getZSPoints(buildingId, floorId, roomId);
        zsAdapter = new ZSRecyclerViewAdapter(this, zsPoints);
        recyclerView.setAdapter(zsAdapter);

        updateFloorRoomText();

        buttonAddZSPoint.setOnClickListener(v -> showAddZSPointDialog());

        buttonUpFloor.setOnClickListener(v -> moveUpFloor());
        buttonDownFloor.setOnClickListener(v -> moveDownFloor());
        buttonPreviousRoom.setOnClickListener(v -> moveToPreviousRoom());
        buttonNextRoom.setOnClickListener(v -> moveToNextRoom());
    }

    private void moveUpFloor() {
        int currentFloorIndex = floors.indexOf(floorId);
        if (currentFloorIndex < floors.size() - 1) {
            floorId = floors.get(currentFloorIndex + 1);
            rooms = myDatabaseHelper.getRooms(buildingId, floorId);
            if (rooms.isEmpty()) {
                myDatabaseHelper.addDummyRoom(buildingId, floorId); // Dodanie pustego pokoju, jeśli lista jest pusta
                rooms = myDatabaseHelper.getRooms(buildingId, floorId);
            }
            roomId = rooms.get(0); // reset room to the first one on the new floor
            refreshList();
            updateFloorRoomText();
        } else {
            Toast.makeText(this, "Already on the top floor", Toast.LENGTH_SHORT).show();
        }
    }

    private void moveDownFloor() {
        int currentFloorIndex = floors.indexOf(floorId);
        if (currentFloorIndex > 0) {
            floorId = floors.get(currentFloorIndex - 1);
            rooms = myDatabaseHelper.getRooms(buildingId, floorId);
            if (rooms.isEmpty()) {
                myDatabaseHelper.addDummyRoom(buildingId, floorId); // Dodanie pustego pokoju, jeśli lista jest pusta
                rooms = myDatabaseHelper.getRooms(buildingId, floorId);
            }
            roomId = rooms.get(0); // reset room to the first one on the new floor
            refreshList();
            updateFloorRoomText();
        } else {
            Toast.makeText(this, "Already on the ground floor", Toast.LENGTH_SHORT).show();
        }
    }

    private void moveToPreviousRoom() {
        int currentRoomIndex = rooms.indexOf(roomId);
        if (currentRoomIndex > 0) {
            roomId = rooms.get(currentRoomIndex - 1);
            refreshList();
            updateFloorRoomText();
        } else {
            Toast.makeText(this, "Already in the first room", Toast.LENGTH_SHORT).show();
        }
    }

    private void moveToNextRoom() {
        int currentRoomIndex = rooms.indexOf(roomId);
        if (currentRoomIndex < rooms.size() - 1) {
            roomId = rooms.get(currentRoomIndex + 1);
            refreshList();
            updateFloorRoomText();
        } else {
            Toast.makeText(this, "Already in the last room", Toast.LENGTH_SHORT).show();
        }
    }

    private void refreshList() {
        List<ZSModel> zsPoints = myDatabaseHelper.getZSPoints(buildingId, floorId, roomId);
        zsAdapter.updateData(zsPoints);
        zsAdapter.notifyDataSetChanged();
    }

    private void updateFloorRoomText() {
        String floorName = myDatabaseHelper.getFloorName(buildingId, floorId);
        String roomName = myDatabaseHelper.getRoomName(buildingId, roomId);
        textViewFloorRoom.setText(String.format("%s - %s", floorName, roomName));
    }

    private void showAddZSPointDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_zs_point, null);
        builder.setView(dialogView);
        AlertDialog dialog = builder.create();

        ZSPointDialogHelper helper = new ZSPointDialogHelper(this, zsAdapter, buildingId, floorId, roomId, result);
        helper.setupButtons(dialogView, dialog);

        dialog.show();
    }
}