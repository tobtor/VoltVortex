package com.example.voltvortex.Activities;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.voltvortex.DataBaseHelper.CreateTableHelpers.ZsTableHelper;
import com.example.voltvortex.DataBaseHelper.MyDatabaseHelper;
import com.example.voltvortex.Helpers.ZSPointDialogHelper;
import com.example.voltvortex.Models.ZSModel;
import com.example.voltvortex.R;
import com.example.voltvortex.RecyclerViewAdapters.ZSRecyclerViewAdapter;

import java.util.List;

public class ZSActivity extends AppCompatActivity {

    int buildingId, floorId, roomId;
    RecyclerView recyclerView;
    ZSRecyclerViewAdapter zsAdapter;
    MyDatabaseHelper myDatabaseHelper;
    Button buttonAddZSPoint, buttonUpFloor, buttonDownFloor, buttonPreviousRoom, buttonNextRoom;
    float result = 0.7f;

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

        recyclerView = findViewById(R.id.recyclerViewZS);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<ZSModel> zsPoints = myDatabaseHelper.getZSPoints(buildingId, floorId, roomId);
        zsAdapter = new ZSRecyclerViewAdapter(this, zsPoints);
        recyclerView.setAdapter(zsAdapter);

        buttonAddZSPoint.setOnClickListener(v -> showAddZSPointDialog());

        // Set click listeners for other buttons
        buttonUpFloor.setOnClickListener(v -> moveUpFloor());
        buttonDownFloor.setOnClickListener(v -> moveDownFloor());
        buttonPreviousRoom.setOnClickListener(v -> moveToPreviousRoom());
        buttonNextRoom.setOnClickListener(v -> moveToNextRoom());
    }

    private void moveUpFloor() {
        // Implement logic to move up a floor
        Toast.makeText(this, "Move up a floor", Toast.LENGTH_SHORT).show();
    }

    private void moveDownFloor() {
        // Implement logic to move down a floor
        Toast.makeText(this, "Move down a floor", Toast.LENGTH_SHORT).show();
    }

    private void moveToPreviousRoom() {
        // Implement logic to move to the previous room
        Toast.makeText(this, "Move to the previous room", Toast.LENGTH_SHORT).show();
    }

    private void moveToNextRoom() {
        // Implement logic to move to the next room
        Toast.makeText(this, "Move to the next room", Toast.LENGTH_SHORT).show();
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
