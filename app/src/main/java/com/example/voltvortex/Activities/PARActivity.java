package com.example.voltvortex.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import com.example.voltvortex.R;

public class PARActivity extends AppCompatActivity {

    int buidlingId;
    RecyclerView recyclerViewPPAR, recyclerViewPAR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_par_activity);
        buidlingId = getIntent().getExtras().getInt("BUILDING_ID");
        recyclerViewPPAR = findViewById(R.id.recyclerViewPPAR);
        recyclerViewPAR = findViewById(R.id.recyclerViewPAR);

    }
}