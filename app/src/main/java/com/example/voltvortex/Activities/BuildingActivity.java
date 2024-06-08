package com.example.voltvortex.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.voltvortex.R;

import java.util.Map;

public class BuildingActivity extends AppCompatActivity {

    Button buttonPAR, buttonZS ;
    int buidlingId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building);
        buidlingId = getIntent().getExtras().getInt("BUILDING_ID");
        buttonPAR = findViewById(R.id.buttonPAR);
        buttonZS = findViewById(R.id.buttonZS);

        buttonPAR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BuildingActivity.this, PARActivity.class);
                intent.putExtra("BUILDING_ID", buidlingId);
                startActivity(intent);
            }
        });

        buttonZS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BuildingActivity.this, FloorAndRoomActivity.class);
                intent.putExtra("BUILDING_ID", buidlingId);
                startActivity(intent);
            }
        });
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


    protected void onStop() {
        super.onStop();
        // Zapisz aktualną aktywność jako ostatnią otwartą
        Bundle extras = new Bundle();
        extras.putInt("BUILDING_ID", buidlingId);
        saveLastActivity(BuildingActivity.class.getSimpleName(), extras);
    }
}