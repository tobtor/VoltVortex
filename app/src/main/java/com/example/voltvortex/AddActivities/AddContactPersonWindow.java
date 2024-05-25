package com.example.voltvortex.AddActivities;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.voltvortex.DataBaseHelper.MyDatabaseHelper;
import com.example.voltvortex.Models.ContactPersonModel;
import com.example.voltvortex.R;

public class AddContactPersonWindow extends AppCompatActivity {

    EditText editTextContactPersonName, editTextContactPersonFirm,
            editTextContactPersonPosition, editTextContactPersonEmail,
            editTextContactPersonPhone;
    TextView textContactPersonName, textContactPersonFirm,
            textContactPersonPosition, textContactPersonEmail,
            textContactPersonPhone;
    Button buttonAddContactPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact_person_window);

        // Inicjalizacja elementów interfejsu
        textContactPersonName  = findViewById(R.id.textContactPersonName);
        editTextContactPersonName = findViewById(R.id.editTextContactPersonName);
        textContactPersonFirm  = findViewById(R.id.textContactPersonFirm);
        editTextContactPersonFirm  = findViewById(R.id.editTextContactPersonFirm);
        textContactPersonPosition = findViewById(R.id.textContactPersonPosition);
        editTextContactPersonPosition = findViewById(R.id.editTextContactPersonPosition);
        textContactPersonEmail = findViewById(R.id.textContactPersonEmail);
        editTextContactPersonEmail = findViewById(R.id.editTextContactPersonEmail);
        textContactPersonPhone = findViewById(R.id.textContactPersonPhone);
        editTextContactPersonPhone = findViewById(R.id.editTextContactPersonPhone);
        buttonAddContactPerson = findViewById(R.id.buttonAddContactPerson);

        buttonAddContactPerson.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addContactPerson();
            }
        });
    }

    private void addContactPerson() {
        String contactPersonName = editTextContactPersonName.getText().toString();
        String contactPersonFirm = editTextContactPersonFirm.getText().toString();
        String contactPersonPosition = editTextContactPersonPosition.getText().toString();
        String contactPersonEmail = editTextContactPersonEmail.getText().toString();
        String contactPersonPhone = editTextContactPersonPhone.getText().toString();

        // Utworzenie nowego obiektu modelu osoby kontaktowej
        ContactPersonModel contactPersonModel = new ContactPersonModel(
                contactPersonName, contactPersonFirm,
                contactPersonPosition, contactPersonEmail, contactPersonPhone);

        // Dodawanie osoby kontaktowej do bazy danych
        MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(this);
        boolean success = myDatabaseHelper.addContactPerson(contactPersonModel);

        Toast.makeText(this, success ? "Osoba kontaktowa dodana pomyślnie" :
                "Błąd przy dodawaniu osoby kontaktowej!", Toast.LENGTH_SHORT).show();

        // Powrót do dodawania projektu
        Intent intent = new Intent(this, AddProjectWindow.class);
        startActivity(intent);
    }
}