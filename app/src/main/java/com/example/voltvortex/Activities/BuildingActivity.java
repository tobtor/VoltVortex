package com.example.voltvortex.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.voltvortex.R;

import java.util.Map;

public class BuildingActivity extends AppCompatActivity {

    Button buttonPAR, buttonZS, buttonBack;
    int buildingId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building);

        SharedPreferences sharedPreferences = getSharedPreferences("ActivityCache", MODE_PRIVATE);
        buildingId = sharedPreferences.getInt("BUILDING_ID", 0);
        buttonPAR = findViewById(R.id.buttonPAR);
        buttonZS = findViewById(R.id.buttonZS);
        buttonBack = findViewById(R.id.buttonBackToProject);

        buttonPAR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BuildingActivity.this, PARActivity.class);
                // Zapisz buildingId do SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("BUILDING_ID", buildingId);
                editor.putString("lastActivity", "PARActivity");
                editor.apply();
                startActivity(intent);
            }
        });

        buttonZS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BuildingActivity.this, FloorAndRoomActivity.class);
                // Zapisz buildingId do SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("BUILDING_ID", buildingId);
                editor.putString("lastActivity", "FloorAndRoomActivity");
                editor.apply();
                startActivity(intent);
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BuildingActivity.this, ProjectActivity.class);
                clearLastActivity();
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

    // Metoda do kasowania informacji o ostatniej aktywności
    private void clearLastActivity() {
        SharedPreferences sharedPreferences = getSharedPreferences("ActivityCache", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("lastActivity");
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
        // Zapisz aktualną aktywność jako ostatnią otwartą tylko w przypadku zamykania aplikacji
        SharedPreferences sharedPreferences = getSharedPreferences("ActivityCache", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("lastActivity", "BuildingActivity");
        editor.putInt("BUILDING_ID", buildingId);
        editor.apply();
    }
}
