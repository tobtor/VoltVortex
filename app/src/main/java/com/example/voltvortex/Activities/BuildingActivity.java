package com.example.voltvortex.Activities;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.voltvortex.R;

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
                Intent intent = new Intent(BuildingActivity.this, PPARActivity.class);
                intent.putExtra("BUILDING_ID", buidlingId);
                startActivity(intent);
            }
        });

        buttonZS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BuildingActivity.this, ZSActivity.class);
                intent.putExtra("BUILDING_ID", buidlingId);
                startActivity(intent);
            }
        });
    }
}