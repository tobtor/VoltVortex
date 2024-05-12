package com.example.voltvortex.AddActivities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.voltvortex.DataBaseHelper.MyDatabaseHelper;
import com.example.voltvortex.Activities.MainActivity;
import com.example.voltvortex.Models.ContactPersonModel;
import com.example.voltvortex.Models.ProjectModel;
import com.example.voltvortex.R;

public class AddProjectWindow extends AppCompatActivity {

    EditText projectName, firm, description;
    Button buttonAddProject, buttonAddContactPerson;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch switchIsSingleContactPerson;
    SearchView searchForContactPerson;
    ListView listOfContactPerson;
    ArrayAdapter<ContactPersonModel> contactPersonArrayAdapter;
    LinearLayout LinearLayoutAddProject, LinearLayoutButtons;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project_window);

        // Inicjalizacja elementów interfejsu
        projectName = findViewById(R.id.editTextProjectName);
        firm = findViewById(R.id.editTextFirm);
        description = findViewById(R.id.editTextDescription);
        buttonAddContactPerson = findViewById(R.id.buttonAddContactPerson);
        buttonAddProject = findViewById(R.id.buttonAddProject);
        switchIsSingleContactPerson = findViewById(R.id.switchIsSinglePerson);
        switchIsSingleContactPerson.setChecked(true);
        searchForContactPerson = findViewById(R.id.serchBarContactPerson);
        listOfContactPerson = findViewById(R.id.listOfContactPerosn);
        listOfContactPerson.setVisibility(View.GONE);

        searchForContactPerson.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                listOfContactPerson.setVisibility(View.VISIBLE);

                return false;
            }
        });

        buttonAddProject.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addProject();
            }
        });
    }

    private void addProject() {
        String projectNameText = projectName.getText().toString();
        String firmText = firm.getText().toString();
        String descriptionText = description.getText().toString();
        boolean isSingleContactPerson = switchIsSingleContactPerson.isChecked();

        // Utworzenie nowego obiektu modelu projektu
        ProjectModel projectModel = new ProjectModel(-1, projectNameText, firmText, descriptionText,
                0, isSingleContactPerson);

        // Dodawanie projektu do bazy danych
        MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(AddProjectWindow.this);
        boolean success = myDatabaseHelper.addProject(projectModel);

        Toast.makeText(AddProjectWindow.this, success ? "Projekt dodany pomyślnie" :
                "Błąd przy dodawaniu projektu", Toast.LENGTH_SHORT).show();

        // Powrót do głównego ekranu aplikacji
        Intent intent = new Intent(AddProjectWindow.this, MainActivity.class);
        startActivity(intent);
    }
}