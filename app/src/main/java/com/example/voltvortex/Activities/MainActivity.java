package com.example.voltvortex.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.TaskStackBuilder;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.voltvortex.Intefraces.RecyclerViewInterface;
import com.example.voltvortex.RecyclerViewAdapters.ProjectRecyclerViewAdapter;
import com.example.voltvortex.AddActivities.AddProjectWindow;
import com.example.voltvortex.DataBaseHelper.MyDatabaseHelper;
import com.example.voltvortex.Models.ProjectModel;
import com.example.voltvortex.R;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerViewInterface {

    Button addProjectButton;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
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
            Intent intent = null;
            switch (lastActivity) {
                case "BuildingActivity":
                    intent = new Intent(this, BuildingActivity.class);
                    break;
                case "FloorAndRoomActivity":
                    intent = new Intent(this, FloorAndRoomActivity.class);
                    break;
                case "PARActivity":
                    intent = new Intent(this, PARActivity.class);
                    break;
                case "ProjectActivity":
                    intent = new Intent(this, ProjectActivity.class);
                    break;
                default:
                    // Brak ostatniej aktywności, kontynuuj normalne uruchamianie MainActivity
                    break;
            }
            if (intent != null) {
                clearLastActivity();  // Wyczyszczenie lastActivity po jej użyciu
                TaskStackBuilder.create(this)
                        .addNextIntentWithParentStack(intent)
                        .startActivities();
                finish();
                return;  // Dodajemy return, aby zakończyć onCreate, jeśli przechodzimy do innej aktywności
            }
        }

        // Przypisanie widoków do zmiennych
        addProjectButton = findViewById(R.id.addProjectButton);
        listOfProjects = findViewById(R.id.listOfProjects);

        // Inicjalizacja klasy pomocniczej bazy danych
        myDatabaseHelper = new MyDatabaseHelper(MainActivity.this);

        // Ustawienie LayoutManager dla RecyclerView
        listOfProjects.setLayoutManager(new LinearLayoutManager(this));

        // Wyświetlenie listy projektów
        getProjectList(myDatabaseHelper);

        // Listener przycisku dodawania projektu
        addProjectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddProjectWindow.class);
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

    @Override
    protected void onStop() {
        super.onStop();
        // Zapisz aktualną aktywność jako ostatnią otwartą
        Bundle extras = new Bundle();
        // Dodaj dodatkowe dane, które chcesz zapamiętać
        saveLastActivity(MainActivity.class.getSimpleName(), extras);
    }

    // Metoda do odświeżania listy projektów
    private void getProjectList(MyDatabaseHelper myDatabaseHelper) {
        List<ProjectModel> projectList = myDatabaseHelper.viewProjectList();
        projectRecyclerViewAdapter = new ProjectRecyclerViewAdapter(projectList, myDatabaseHelper, this);
        listOfProjects.setAdapter(projectRecyclerViewAdapter);
    }

    @Override
    public void onItemClicked(int position) {
        ProjectModel projectModel = projectRecyclerViewAdapter.getProjectAt(position);
        // Zapisz dane do SharedPreferences zamiast przekazywać przez Intent
        SharedPreferences sharedPreferences = getSharedPreferences("ActivityCache", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("PROJECT_ID", projectModel.getProjectID());
        editor.putInt("CONTACT_PERSON_ID", projectModel.getContactPersonID());
        editor.putString("lastActivity", "ProjectActivity");
        editor.apply();
        Intent intent = new Intent(MainActivity.this, ProjectActivity.class);
        startActivity(intent);
    }
}
