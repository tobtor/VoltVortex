package com.example.voltvortex.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.voltvortex.Intefraces.RecyclerViewInterface;
import com.example.voltvortex.RecyclerViewAdapters.ProjectRecyclerViewAdapter;
import com.example.voltvortex.AddActivities.AddProjectWindow;
import com.example.voltvortex.DataBaseHelper.MyDatabaseHelper;
import com.example.voltvortex.Models.ProjectModel;
import com.example.voltvortex.R;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements RecyclerViewInterface {

    Button addProjectButton;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch usuwanie;
    RecyclerView listOfProjects;
    MyDatabaseHelper myDatabaseHelper;
    ProjectRecyclerViewAdapter projectRecyclerViewAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences("ActivityCache", MODE_PRIVATE);
        String lastActivity = sharedPreferences.getString("lastActivity", null);
        if (lastActivity != null) {
            Intent intent;
            Bundle extras = getLastActivityData();
            switch (lastActivity) {
                case "BuildingActivity":
                    intent = new Intent(this, BuildingActivity.class);
                    intent.putExtras(extras);
                    startActivity(intent);
                    finish();
                    break;
                case "FloorAndRoomActivity":
                    intent = new Intent(this, FloorAndRoomActivity.class);
                    intent.putExtras(extras);
                    startActivity(intent);
                    finish();
                    break;
                case "PARActivity":
                    intent = new Intent(this, PARActivity.class);
                    intent.putExtras(extras);
                    startActivity(intent);
                    finish();
                    break;
                case "ProjectActivity":
                    intent = new Intent(this, ProjectActivity.class);
                    intent.putExtras(extras);
                    startActivity(intent);
                    finish();
                    break;
                default:
                    // Brak ostatniej aktywności, kontynuuj normalne uruchamianie MainActivity
                    break;
            }
        }

        // Przypisanie widoków do zmiennych
        addProjectButton = findViewById(R.id.addProjectButton);
        listOfProjects = findViewById(R.id.listOfProjects);
        usuwanie = findViewById(R.id.usuwanie);

        // Inicjalizacja klasy pomocniczej bazy danych
        myDatabaseHelper = new MyDatabaseHelper(MainActivity.this);

        // Ustawienie LayoutManager dla RecyclerView
        listOfProjects.setLayoutManager(new LinearLayoutManager(this));

        // Wyświetlenie listy projektów
        getProjectList(myDatabaseHelper);

        // Listener zmiany stanu przełącznika usuwanie
        usuwanie.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                projectRecyclerViewAdapter.setDeleteMode(isChecked);
            }
        });

        // Listener przycisku dodawania projektu
        addProjectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddProjectWindow.class);
                startActivity(intent);
            }
        });
    }

    // Metoda do odświeżania listy projektów
    private void getProjectList(MyDatabaseHelper myDatabaseHelper) {
        List<ProjectModel> projectList = myDatabaseHelper.viewProjectList();
        projectRecyclerViewAdapter = new ProjectRecyclerViewAdapter(projectList, myDatabaseHelper, this);
        listOfProjects.setAdapter(projectRecyclerViewAdapter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Zapisz aktualną aktywność jako ostatnią otwartą
        Bundle extras = new Bundle();
        // Dodaj dodatkowe dane, które chcesz zapamiętać
        saveLastActivity(MainActivity.class.getSimpleName(), extras);
    }

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
    public void onItemClicked(int position) {
        ProjectModel projectModel = projectRecyclerViewAdapter.getProjectAt(position);
        Intent intent = new Intent(MainActivity.this, ProjectActivity.class);
        intent.putExtra("PROJECT_ID", projectModel.getProjectID());
        intent.putExtra("CONTACT_PERSON_ID", projectModel.getContactPersonID());
        startActivity(intent);
    }
}