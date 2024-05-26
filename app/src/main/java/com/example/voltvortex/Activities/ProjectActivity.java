package com.example.voltvortex.Activities;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.voltvortex.AddActivities.AddBuildingActicity;
import com.example.voltvortex.AddActivities.AddProjectWindow;
import com.example.voltvortex.DataBaseHelper.MyDatabaseHelper;
import com.example.voltvortex.Intefraces.RecyclerViewInterface;
import com.example.voltvortex.Models.BuildingModel;
import com.example.voltvortex.Models.ProjectModel;
import com.example.voltvortex.R;
import com.example.voltvortex.RecyclerViewAdapters.BuildingRecyclerViewAdapter;
import com.example.voltvortex.RecyclerViewAdapters.ProjectRecyclerViewAdapter;

import java.util.List;

public class ProjectActivity extends AppCompatActivity implements RecyclerViewInterface {

    Button addBuildingButton;
    MyDatabaseHelper myDatabaseHelper;
    BuildingRecyclerViewAdapter buildingRecyclerViewAdapter;
    RecyclerView listOfBuildings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
        int projectId = getIntent().getExtras().getInt("PROJECT_ID");
        int contactPersonId = getIntent().getExtras().getInt("CONTACT_PERSON_ID");

        addBuildingButton = findViewById(R.id.buttonAddBuidling);
        listOfBuildings = findViewById(R.id.listOfBuildings);
        listOfBuildings.setLayoutManager(new LinearLayoutManager(this));

        getBuildingList(myDatabaseHelper);

        addBuildingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProjectActivity.this, AddBuildingActicity.class);
                intent.putExtra("PROJECT_ID", projectId);
                intent.putExtra("CONTACT_PERSON_ID", contactPersonId);
                startActivity(intent);
            }
        });
    }

    // Metoda do odświeżania listy budynków
    private void getBuildingList(MyDatabaseHelper myDatabaseHelper) {
        List<BuildingModel> buildinglist = myDatabaseHelper.viewBuildingList();
        buildingRecyclerViewAdapter = new BuildingRecyclerViewAdapter(buildinglist,
                myDatabaseHelper, this);
        listOfBuildings.setAdapter(buildingRecyclerViewAdapter);
    }

    @Override
    public void onItemClicked(int position) {
        BuildingModel buildingModel = buildingRecyclerViewAdapter.getBuildingAt(position);
        Intent intent = new Intent(ProjectActivity.this, BuildingActivity.class);
        startActivity(intent);
    }
}