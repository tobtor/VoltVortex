package com.example.voltvortex.AddActivities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.voltvortex.Activities.ProjectActivity;
import com.example.voltvortex.DataBaseHelper.MyDatabaseHelper;
import com.example.voltvortex.Models.BuildingModel;
import com.example.voltvortex.Models.ContactPersonModel;
import com.example.voltvortex.R;
import com.example.voltvortex.RecyclerViewAdapters.ContactPersonRecyclerViewAdapter;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class AddBuildingActicity extends AppCompatActivity {

    EditText editTextBuildingName, editTextCity, editTextPostCode, editTextStreet, editTextBuildingNumber;
    TextView textBuildingName, textCity, textPostCode, textStreet, textBuildingNumber;
    Button buttonAddContactPerson, buttonAddBuilding;
    int projectId, contactPersonId;
    MyDatabaseHelper myDatabaseHelper;
    RecyclerView listOfContactPerson;
    ContactPersonRecyclerViewAdapter contactPersonRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_building);

        projectId = getIntent().getExtras().getInt("PROJECT_ID");
        contactPersonId = getIntent().getExtras().getInt("CONTACT_PERSON_ID");
        textBuildingName  = findViewById(R.id.textBuildingName);
        editTextBuildingName = findViewById(R.id.editTextBuildingName);
        textCity  = findViewById(R.id.textCity);
        editTextCity  = findViewById(R.id.editTextCity);
        textPostCode  = findViewById(R.id.textPostCode);
        editTextPostCode  = findViewById(R.id.editTextPostCode);
        textStreet  = findViewById(R.id.textStreet);
        editTextStreet  = findViewById(R.id.editTextStreet);
        textBuildingNumber  = findViewById(R.id.textBuildingNumber);
        editTextBuildingNumber  = findViewById(R.id.editTextBuildingNumber);
        buttonAddContactPerson  = findViewById(R.id.buttonAddContactPerson);
        buttonAddBuilding = findViewById(R.id.buttonAddBuidling);
        listOfContactPerson = findViewById(R.id.listOfContactPerson);

        myDatabaseHelper = new MyDatabaseHelper(this);

        listOfContactPerson.setLayoutManager(new LinearLayoutManager(this));
        getContactPersonList(myDatabaseHelper);

        buttonAddContactPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        buttonAddBuilding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBuilding(projectId, contactPersonId);
            }
        });
    }

    private void addBuilding(int projectId, int contactPersonId){
        String buildingName = editTextBuildingName.getText().toString();
        String city = editTextCity.getText().toString();
        String postCode = editTextPostCode.getText().toString();
        String street = editTextStreet.getText().toString();
        String buildingNumber = editTextBuildingNumber.getText().toString();

        Calendar calendar = Calendar.getInstance();
        Date dateOfMesurments = calendar.getTime();

        BuildingModel buildingModel = new BuildingModel(buildingName, dateOfMesurments,
                city, postCode, street, buildingNumber, projectId, contactPersonId);

        boolean success = myDatabaseHelper.addBuilding(buildingModel);

        Toast.makeText(this,
                success ? "Budynek dodany pomyślnie" : "Błąd przy dodawaniu budynku!",
                Toast.LENGTH_SHORT)
                .show();

        if (success) {
            Intent intent = new Intent(this, ProjectActivity.class);
            intent.putExtra("CONTACT_PERSON_ID", contactPersonId);
            intent.putExtra("PROJECT_ID", projectId);
            startActivity(intent);
        }
    }

    private void getContactPersonList(MyDatabaseHelper myDatabaseHelper) {
        List<ContactPersonModel> contactPersonList = myDatabaseHelper.viewContactPersonList();
        contactPersonRecyclerViewAdapter = new ContactPersonRecyclerViewAdapter(contactPersonList, myDatabaseHelper);
        contactPersonRecyclerViewAdapter.setOnItemClickListener(new ContactPersonRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int contactPersonID) {
                contactPersonId = contactPersonID;
            }
        });
        listOfContactPerson.setAdapter(contactPersonRecyclerViewAdapter);
    }
}