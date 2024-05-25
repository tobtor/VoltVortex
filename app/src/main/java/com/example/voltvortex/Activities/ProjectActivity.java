package com.example.voltvortex.Activities;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.voltvortex.AddActivities.AddBuildingActicity;
import com.example.voltvortex.AddActivities.AddProjectWindow;
import com.example.voltvortex.DataBaseHelper.MyDatabaseHelper;
import com.example.voltvortex.R;
import com.example.voltvortex.RecyclerViewAdapters.BuildingRecyclerViewAdapter;

public class ProjectActivity extends AppCompatActivity {

    Button addBuildingButton;
    MyDatabaseHelper myDatabaseHelper;
    BuildingRecyclerViewAdapter buildingRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
        int projectId = getIntent().getExtras().getInt("ID");

        addBuildingButton = findViewById(R.id.buttonAddBuidling);

        addBuildingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProjectActivity.this, AddBuildingActicity.class);
                intent.putExtra("ID", projectId);
                startActivity(intent);
            }
        });

    }
}