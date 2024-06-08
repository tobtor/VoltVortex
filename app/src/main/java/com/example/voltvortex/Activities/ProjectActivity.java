package com.example.voltvortex.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.voltvortex.AddActivities.AddBuildingActicity;
import com.example.voltvortex.DataBaseHelper.MyDatabaseHelper;
import com.example.voltvortex.Intefraces.RecyclerViewInterface;
import com.example.voltvortex.Models.BuildingModel;
import com.example.voltvortex.R;
import com.example.voltvortex.RecyclerViewAdapters.BuildingRecyclerViewAdapter;

import java.util.List;
import java.util.Map;

public class ProjectActivity extends AppCompatActivity implements RecyclerViewInterface {

    Button addBuildingButton;
    MyDatabaseHelper myDatabaseHelper;
    BuildingRecyclerViewAdapter buildingRecyclerViewAdapter;
    RecyclerView listOfBuildings;
    int projectId = 0, contactPersonId = 0;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);

        projectId = getIntent().getExtras().getInt("PROJECT_ID");
        contactPersonId = getIntent().getExtras().getInt("CONTACT_PERSON_ID");
        addBuildingButton = findViewById(R.id.buttonAddBuidling);
        listOfBuildings = findViewById(R.id.listOfBuildings);

        myDatabaseHelper = new MyDatabaseHelper(ProjectActivity.this);

        listOfBuildings.setLayoutManager(new LinearLayoutManager(this));

        getBuildingList(myDatabaseHelper, projectId);

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
    private void getBuildingList(MyDatabaseHelper myDatabaseHelper, int projectId) {
        if (projectId == 0){
            Toast.makeText(context, "Nie udało się uzyskać listy budynków!", Toast.LENGTH_SHORT).show();
        } else {
            List<BuildingModel> buildinglist = myDatabaseHelper.viewBuildingListByProjectId(projectId);
            buildingRecyclerViewAdapter = new BuildingRecyclerViewAdapter(buildinglist,
                    myDatabaseHelper, this);
            listOfBuildings.setAdapter(buildingRecyclerViewAdapter);
        }
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
    protected void onStop() {
        super.onStop();
        // Zapisz aktualną aktywność jako ostatnią otwartą
        Bundle extras = new Bundle();
        extras.putInt("PROJECT_ID", projectId);
        extras.putInt("CONTACT_PERSON_ID", contactPersonId);
        saveLastActivity(ProjectActivity.class.getSimpleName(), extras);
    }

    @Override
    public void onItemClicked(int position) {
        BuildingModel buildingModel = buildingRecyclerViewAdapter.getBuildingAt(position);
        Intent intent = new Intent(ProjectActivity.this, BuildingActivity.class);
        intent.putExtra("BUILDING_ID", buildingModel.getBuildingID());
        startActivity(intent);
    }
}