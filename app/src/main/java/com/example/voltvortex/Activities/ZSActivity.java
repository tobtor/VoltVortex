package com.example.voltvortex.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.voltvortex.R;

public class ZSActivity extends AppCompatActivity {

    int buidlingId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zs_activity);
        buidlingId = getIntent().getExtras().getInt("BUILDING_ID");
    }
}