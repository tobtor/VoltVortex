package com.example.voltvortex.Activities;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.voltvortex.DataBaseHelper.MyDatabaseHelper;
import com.example.voltvortex.Models.FloorModel;
import com.example.voltvortex.Models.RoomModel;
import com.example.voltvortex.R;
import com.example.voltvortex.RecyclerViewAdapters.FloorRecyclerViewAdapter;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public class FloorAndRoomActivity extends AppCompatActivity {

    int buildingId;
    RecyclerView recyclerView;
    FloorRecyclerViewAdapter floorAdapter;
    MyDatabaseHelper myDatabaseHelper;
    Button addFloorButton, addRoomButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floor_and_room);

        SharedPreferences sharedPreferences = getSharedPreferences("ActivityCache", MODE_PRIVATE);
        buildingId = sharedPreferences.getInt("BUILDING_ID", 0);
        myDatabaseHelper = new MyDatabaseHelper(this);

        recyclerView = findViewById(R.id.recyclerViewFloorAndRoom);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        addFloorButton = findViewById(R.id.buttonAddFloor);
        addRoomButton = findViewById(R.id.buttonAddRoom);

        List<FloorModel> floors = myDatabaseHelper.getFloors(buildingId);
        floorAdapter = new FloorRecyclerViewAdapter(this, floors, buildingId);
        recyclerView.setAdapter(floorAdapter);

        addFloorButton.setOnClickListener(v -> showAddFloorDialog());
        addRoomButton.setOnClickListener(v -> showAddRoomDialog());
    }

    private void showAddFloorDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_floor, null);
        builder.setView(dialogView);

        EditText editTextFloorName = dialogView.findViewById(R.id.editTextFloorName);
        Button buttonCancelAddFloor = dialogView.findViewById(R.id.buttonCancelAddFloor);
        Button buttonConfirmAddFloor = dialogView.findViewById(R.id.buttonConfirmAddFloor);

        AlertDialog alertDialog = builder.create();

        buttonCancelAddFloor.setOnClickListener(v -> alertDialog.dismiss());

        buttonConfirmAddFloor.setOnClickListener(v -> {
            String floorName = editTextFloorName.getText().toString();
            FloorModel floor = new FloorModel(floorName);
            myDatabaseHelper.addFloor(buildingId, floor);
            refreshFloorList();
            alertDialog.dismiss();
        });

        alertDialog.show();
    }

    private void showAddRoomDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_room, null);
        builder.setView(dialogView);

        NumberPicker numberPickerFloor = dialogView.findViewById(R.id.numberPickerFloor);
        EditText editTextRoomName = dialogView.findViewById(R.id.editTextRoomName);
        Button buttonCancelAddRoom = dialogView.findViewById(R.id.buttonCancelAddRoom);
        Button buttonConfirmAddRoom = dialogView.findViewById(R.id.buttonConfirmAddRoom);

        // Pobierz listę nazw pięter
        List<String> floorNames = myDatabaseHelper.getFloorNames(buildingId);
        String[] floorNameArray = floorNames.toArray(new String[0]);

        // Ustaw wartości w NumberPicker
        numberPickerFloor.setMinValue(0);
        numberPickerFloor.setMaxValue(floorNameArray.length - 1);
        numberPickerFloor.setDisplayedValues(floorNameArray);

        // Zmień kolor tekstu w NumberPicker na biały
        setNumberPickerTextColor(numberPickerFloor, Color.WHITE);

        AlertDialog alertDialog = builder.create();

        buttonCancelAddRoom.setOnClickListener(v -> alertDialog.dismiss());

        buttonConfirmAddRoom.setOnClickListener(v -> {
            String roomName = editTextRoomName.getText().toString();
            int selectedFloorIndex = numberPickerFloor.getValue();
            int selectedFloorId = myDatabaseHelper.getFloorIdByName(buildingId, floorNameArray[selectedFloorIndex]);
            RoomModel room = new RoomModel(roomName, selectedFloorId);
            myDatabaseHelper.addRoom(buildingId, room);
            refreshFloorList();
            alertDialog.dismiss();
        });

        alertDialog.show();
    }

    private boolean setNumberPickerTextColor(NumberPicker numberPicker, int color) {
        final int count = numberPicker.getChildCount();
        for (int i = 0; i < count; i++) {
            View child = numberPicker.getChildAt(i);
            if (child instanceof EditText) {
                try {
                    @SuppressLint("SoonBlockedPrivateApi") Field selectorWheelPaintField = numberPicker.getClass().getDeclaredField("mSelectorWheelPaint");
                    selectorWheelPaintField.setAccessible(true);
                    ((Paint) selectorWheelPaintField.get(numberPicker)).setColor(color);
                    ((EditText) child).setTextColor(color);
                    numberPicker.invalidate();
                    return true;
                } catch (NoSuchFieldException | IllegalAccessException | IllegalArgumentException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    private void refreshFloorList() {
        List<FloorModel> floors = myDatabaseHelper.getFloors(buildingId);
        floorAdapter.updateFloors(floors);
    }

    // Metoda do zapisu danych aktywności do SharedPreferences
    private void saveLastActivity(String activityName, Bundle extras) {
        SharedPreferences sharedPreferences = getSharedPreferences("ActivityCache", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("lastActivity", activityName);
        for (String key : extras.keySet()) {
            Object value = extras.get(key);
            if (value instanceof Integer) {
                editor.putInt(key, (Integer) value);
            } else if (value instanceof String) {
                editor.putString(key, (String) value);
            }
        }
        editor.apply();
    }

    // Metoda do odczytu danych aktywności z SharedPreferences
    private Bundle getLastActivityData() {
        SharedPreferences sharedPreferences = getSharedPreferences("ActivityCache", MODE_PRIVATE);
        Bundle extras = new Bundle();
        Map<String, ?> allEntries = sharedPreferences.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            if (entry.getValue() instanceof Integer) {
                extras.putInt(entry.getKey(), (Integer) entry.getValue());
            } else if (entry.getValue() instanceof String) {
                extras.putString(entry.getKey(), (String) entry.getValue());
            }
        }
        return extras;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        // Zapisz aktualną aktywność jako ostatnią otwartą
        Bundle extras = new Bundle();
        extras.putInt("BUILDING_ID", buildingId);
        saveLastActivity(FloorAndRoomActivity.class.getSimpleName(), extras);
    }
}
