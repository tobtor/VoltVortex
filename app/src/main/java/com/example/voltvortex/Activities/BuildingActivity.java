package com.example.voltvortex.Activities;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.voltvortex.AddActivities.AddProjectWindow;
import com.example.voltvortex.R;

public class BuildingActivity extends AppCompatActivity {

    Button buttonPAR, buttonZS ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building);

        buttonPAR = findViewById(R.id.buttonPAR);
        buttonZS = findViewById(R.id.buttonZS);

        buttonPAR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BuildingActivity.this, PARActivity.class);
                startActivity(intent);
            }
        });

        buttonZS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BuildingActivity.this, ZSActivity.class);
                startActivity(intent);
            }
        });
    }
}