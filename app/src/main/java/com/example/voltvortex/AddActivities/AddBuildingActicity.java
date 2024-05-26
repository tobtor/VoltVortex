package com.example.voltvortex.AddActivities;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.voltvortex.Models.BuildingModel;
import com.example.voltvortex.R;

import java.util.Date;

public class AddBuildingActicity extends AppCompatActivity {

    EditText editTextBuildingName, editTextCity, editTextPostCode, editTextStreet, editTextBuildingNumber;
    TextView textBuildingName, textCity, textPostCode, textStreet, textBuildingNumber;
    Button buttonAddContactPerson, buttonAddBuilding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_building);

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

        Date date = new Date();
        date.getTime();
        System.out.println(date);

        buttonAddContactPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        buttonAddBuilding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void addBuilding(){
        String buildingName = editTextBuildingName.getText().toString();
        String city = editTextCity.getText().toString();
        String postCode = editTextPostCode.getText().toString();
        String street = editTextStreet.getText().toString();
        String buildingNumber = editTextBuildingNumber.getText().toString();
        Date date = new Date();
        date.getTime();
        /*BuildingModel buildingModel = new BuildingModel(buildingName, )*/

    }


}