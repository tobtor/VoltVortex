package com.example.voltvortex.Activities;

import android.app.AlertDialog;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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
import java.util.concurrent.atomic.AtomicBoolean;

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
        zsAdapter = new ZSRecyclerViewAdapter(this, zsPoints, buildingId);
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

    public void showZSPointDetails(ZSModel zsPoint) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.item_zs_faults, null);
        builder.setView(dialogView);

        Button buttonBZ = dialogView.findViewById(R.id.buttonBZ);
        Button buttonBPE = dialogView.findViewById(R.id.buttonBPE);
        Button buttonBK = dialogView.findViewById(R.id.buttonBK);
        Button buttonBKlapki = dialogView.findViewById(R.id.buttonBKlapki);
        Button buttonWyrw = dialogView.findViewById(R.id.buttonWyrw);
        Button button2Przew = dialogView.findViewById(R.id.button2Przew);

        setUpToggleButton(buttonBZ, zsPoint, ZsTableHelper.getColumnIsBz());
        setUpToggleButton(buttonBPE, zsPoint, ZsTableHelper.getColumnIsBpe());
        setUpToggleButton(buttonBK, zsPoint, ZsTableHelper.getColumnIsBK());
        setUpToggleButton(buttonBKlapki, zsPoint, ZsTableHelper.getColumnIsBKLAPKI());
        setUpToggleButton(buttonWyrw, zsPoint, ZsTableHelper.getColumnIsWyrw());
        setUpToggleButton(button2Przew, zsPoint, ZsTableHelper.getColumnIs2przew());

        builder.setPositiveButton("OK", null);
        builder.create().show();
    }

    private void setUpToggleButton(Button button, ZSModel zsPoint, String column) {
        AtomicBoolean currentValue = new AtomicBoolean(getCurrentParameterValue(zsPoint, column));
        updateButtonColor(button, currentValue.get());

        button.setOnClickListener(v -> {
            boolean newValue = !currentValue.get();
            myDatabaseHelper.updateParameter(buildingId, zsPoint.getZsID(), column, newValue);
            updateButtonColor(button, newValue);
            currentValue.set(newValue); // Aktualizuj currentValue dla następnych kliknięć

            // Zaktualizuj wartość w modelu ZSModel
            setNewParameterValue(zsPoint, column, newValue);
            refreshList(); // Odświeżenie listy po zmianie stanu
        });
    }

    private boolean getCurrentParameterValue(ZSModel zsPoint, String column) {
        switch (column) {
            case "IS_BZ":
                return zsPoint.isBZ();
            case "IS_BPE":
                return zsPoint.isBPE();
            case "IS_BK":
                return zsPoint.isBK();
            case "IS_BKLAPKI":
                return zsPoint.isBKLAPKI();
            case "IS_WYRW":
                return zsPoint.isWYRW();
            case "IS_2PRZEW":
                return zsPoint.isIs2PRZEW();
            default:
                return false;
        }
    }

    private void setNewParameterValue(ZSModel zsPoint, String column, boolean value) {
        switch (column) {
            case "IS_BZ":
                zsPoint.setBZ(value);
                break;
            case "IS_BPE":
                zsPoint.setBPE(value);
                break;
            case "IS_BK":
                zsPoint.setBK(value);
                break;
            case "IS_BKLAPKI":
                zsPoint.setBKLAPKI(value);
                break;
            case "IS_WYRW":
                zsPoint.setWYRW(value);
                break;
            case "IS_2PRZEW":
                zsPoint.setIs2PRZEW(value);
                break;
        }
    }

    private void updateButtonColor(Button button, boolean isActive) {
        int color = isActive ? getResources().getColor(R.color.buttonNormalColor) : getResources().getColor(R.color.buttonHighLightColor);
        button.setBackgroundTintList(ColorStateList.valueOf(color));
    }
}