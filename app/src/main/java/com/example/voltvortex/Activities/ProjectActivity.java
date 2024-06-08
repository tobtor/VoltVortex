package com.example.voltvortex.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.voltvortex.AddActivities.AddBuildingActicity;
import com.example.voltvortex.DataBaseHelper.MyDatabaseHelper;
import com.example.voltvortex.Intefraces.RecyclerViewInterface;
import com.example.voltvortex.Models.BuildingModel;
import com.example.voltvortex.R;
import com.example.voltvortex.RecyclerViewAdapters.BuildingRecyclerViewAdapter;
import java.util.List;

public class ProjectActivity extends AppCompatActivity implements RecyclerViewInterface {

    Button addBuildingButton, backToMainButton;
    MyDatabaseHelper myDatabaseHelper;
    BuildingRecyclerViewAdapter buildingRecyclerViewAdapter;
    RecyclerView listOfBuildings;
    int projectId = 0, contactPersonId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);

        // Pobierz dane z SharedPreferences zamiast Intent
        SharedPreferences sharedPreferences = getSharedPreferences("ActivityCache", MODE_PRIVATE);
        projectId = sharedPreferences.getInt("PROJECT_ID", 0);
        contactPersonId = sharedPreferences.getInt("CONTACT_PERSON_ID", 0);

        // Przypisanie widoków do zmiennych
        addBuildingButton = findViewById(R.id.buttonAddBuidling);
        backToMainButton = findViewById(R.id.buttonBackToMain);
        listOfBuildings = findViewById(R.id.listOfBuildings);

        myDatabaseHelper = new MyDatabaseHelper(ProjectActivity.this);

        listOfBuildings.setLayoutManager(new LinearLayoutManager(this));

        getBuildingList(myDatabaseHelper, projectId);

        addBuildingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProjectActivity.this, AddBuildingActicity.class);
                // Możesz zapisać nowe dane do SharedPreferences, jeśli potrzebujesz
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("PROJECT_ID", projectId);
                editor.putInt("CONTACT_PERSON_ID", contactPersonId);
                editor.putString("lastActivity", "ProjectActivity");
                editor.apply();
                startActivity(intent);
            }
        });

        backToMainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearLastActivity();
                Intent intent = new Intent(ProjectActivity.this, MainActivity.class);
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

    // Metoda do odświeżania listy budynków
    private void getBuildingList(MyDatabaseHelper myDatabaseHelper, int projectId) {
        if (projectId == 0) {
            // Pokaż wiadomość, jeśli nie udało się uzyskać listy budynków
        } else {
            List<BuildingModel> buildingList = myDatabaseHelper.viewBuildingListByProjectId(projectId);
            buildingRecyclerViewAdapter = new BuildingRecyclerViewAdapter(buildingList, myDatabaseHelper, this);
            listOfBuildings.setAdapter(buildingRecyclerViewAdapter);
        }
    }

    @Override
    public void onItemClicked(int position) {
        BuildingModel buildingModel = buildingRecyclerViewAdapter.getBuildingAt(position);
        Intent intent = new Intent(ProjectActivity.this, BuildingActivity.class);
        // Możesz zapisać nowe dane do SharedPreferences, jeśli potrzebujesz
        SharedPreferences sharedPreferences = getSharedPreferences("ActivityCache", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("BUILDING_ID", buildingModel.getBuildingID());
        editor.putString("lastActivity", "BuildingActivity");
        editor.apply();
        startActivity(intent);
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
        editor.putString("lastActivity", "ProjectActivity");
        editor.putInt("PROJECT_ID", projectId);
        editor.putInt("CONTACT_PERSON_ID", contactPersonId);
        editor.apply();
    }
}