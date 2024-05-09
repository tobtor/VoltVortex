package com.example.voltvortex;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.voltvortex.DataBaseHelper.MyDatabaseHelper;

public class AddProjectWindow extends AppCompatActivity {

    EditText projectName, cityName, postcode, contactPesonName, contactPersonPhoneNumber;
    Button buttonEndStep1;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch swichIsManyCities, switchIsManyContactPerson;
    LinearLayout LinearLayoutProjectCity, LinearLayoutProjectContactPerson;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproject_window);

        projectName = findViewById(R.id.editTextNazwaProjektu);
        cityName = findViewById(R.id.editTextMiasto);
        postcode = findViewById(R.id.editTextKodPocztowy);
        contactPesonName = findViewById(R.id.editTextOsobaKontaktowa);
        contactPersonPhoneNumber = findViewById(R.id.editTextNumerTelefonu);
        buttonEndStep1 = findViewById(R.id.buttonEndStep1);
        swichIsManyCities = findViewById(R.id.switchDodajProjektPytanie1);
        switchIsManyContactPerson = findViewById(R.id.switchDodajProjektPytanie2);
        LinearLayoutProjectCity = findViewById(R.id.LinearLayoutProjectCity);
        LinearLayoutProjectContactPerson = findViewById(R.id.LinearLayoutProjectContactPerson);

        swichIsManyCities.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    LinearLayoutProjectCity.setVisibility(View.GONE);
                } else {
                    LinearLayoutProjectCity.setVisibility(View.VISIBLE);
                }
            }
        });

        switchIsManyContactPerson.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    LinearLayoutProjectContactPerson.setVisibility(View.GONE);
                } else {
                    LinearLayoutProjectContactPerson.setVisibility(View.VISIBLE);
                }
            }
        });

        buttonEndStep1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                ProjectModel projectModel;

                try {
                    projectModel = new ProjectModel(-1, projectName.getText().toString(),
                            swichIsManyCities.isChecked(), switchIsManyContactPerson.isChecked());
                        Toast.makeText(AddProjectWindow.this, projectModel.toString(), Toast.LENGTH_SHORT).show();
                    }
                    catch (Exception e){
                        Toast.makeText(AddProjectWindow.this, "Błąd przy dodawaniu!", Toast.LENGTH_SHORT).show();
                        projectModel = new ProjectModel(-1, "error",
                                swichIsManyCities.isChecked(), switchIsManyContactPerson.isChecked());
                    }

                    MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(AddProjectWindow.this);

                    boolean success = myDatabaseHelper.addProject(projectModel);

                    Toast.makeText(AddProjectWindow.this, "Success " + success, Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(AddProjectWindow.this, MainActivity.class);
                    startActivity(intent);

                }
            });



    }
}