package com.example.voltvortex.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.voltvortex.DataBaseHelper.MyDatabaseHelper;
import com.example.voltvortex.Intefraces.RecyclerViewInterface;
import com.example.voltvortex.Models.PARModel;
import com.example.voltvortex.R;
import com.example.voltvortex.RecyclerViewAdapters.PARRecyclerViewAdapter;

import java.util.List;
import java.util.Map;

public class PARActivity extends AppCompatActivity implements RecyclerViewInterface {

    int buidlingId;
    RecyclerView recyclerViewPAR;
    MyDatabaseHelper myDatabaseHelper;
    PARRecyclerViewAdapter parRecyclerViewAdapter;
    Button buttonAddPAR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_par_activity);
        buidlingId = getIntent().getExtras().getInt("BUILDING_ID");
        recyclerViewPAR = findViewById(R.id.recyclerViewPAR);
        buttonAddPAR = findViewById(R.id.buttonAddPAR);

        myDatabaseHelper = new MyDatabaseHelper(this);

        recyclerViewPAR.setLayoutManager(new LinearLayoutManager(this));

        getPARList(myDatabaseHelper, buidlingId);

        buttonAddPAR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddParDialog();
            }
        });
    }

    private void getPARList(MyDatabaseHelper myDatabaseHelper, int buidlingId) {
        List<PARModel> pparList = myDatabaseHelper.viewPARList(buidlingId);
        if (pparList.isEmpty()) {
            Toast.makeText(this, "Nie udało się uzyskać listy PPAR!", Toast.LENGTH_SHORT).show();
        } else {
            parRecyclerViewAdapter = new PARRecyclerViewAdapter(pparList,
                    myDatabaseHelper,
                    this,
                    buidlingId,
                    this);
            recyclerViewPAR.setAdapter(parRecyclerViewAdapter);
        }
    }

    private void showAddParDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_par, null);
        builder.setView(dialogView);

        final EditText editTextContent = dialogView.findViewById(R.id.editTextPARContent);
        Button confirmPAR = dialogView.findViewById(R.id.buttonConfirmAddPAR);
        Button cancelPAR = dialogView.findViewById(R.id.buttonCancelAddPAR);

        // Tworzenie i wyświetlanie AlertDialog
        AlertDialog dialog = builder.create();

        // Ustawienie działania przycisku potwierdzającego
        confirmPAR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = editTextContent.getText().toString();
                if (!content.isEmpty()) {
                    addNewPar(content, buidlingId); // Uwzględnij odpowiednie argumenty metody, jeśli jest to konieczne
                    dialog.dismiss(); // Zamknij dialog po potwierdzeniu
                } else {
                    Toast.makeText(PARActivity.this, "Content cannot be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Ustawienie działania przycisku anulującego
        cancelPAR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss(); // Zamknij dialog po anulowaniu
            }
        });

        dialog.show();
    }


    private void addNewPar(String content, int buidlingId) {
        // Tworzenie nowego obiektu PARModel
        PARModel newPar = new PARModel(content, 0);

        // Dodawanie nowego PAR do bazy danych
        boolean result = myDatabaseHelper.addPAR(newPar, buidlingId);

        // Sprawdzenie wyniku operacji dodania
        if (result) {
            getPARList(myDatabaseHelper, buidlingId);
            parRecyclerViewAdapter.notifyDataSetChanged();
            Toast.makeText(this, "PAR added successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to add PAR", Toast.LENGTH_SHORT).show();
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
        extras.putInt("BUILDING_ID", buidlingId);
        saveLastActivity(PARActivity.class.getSimpleName(), extras);
    }

    @Override
    public void onItemClicked(int position) {
        PARModel clickedPAR = parRecyclerViewAdapter.getPARAt(position);
        int newIsUsed = clickedPAR.getIsUsed() == 1 ? 0 : 1;

        Log.d("onItemClicked", "Clicked position: " + position);
        Log.d("onItemClicked", "PAR ID: " + clickedPAR.getParID());
        Log.d("onItemClicked", "Old isUsed: " + clickedPAR.getIsUsed());
        Log.d("onItemClicked", "New isUsed: " + newIsUsed);

        clickedPAR.setIsUsed(newIsUsed);

        boolean isUpdated = myDatabaseHelper.updatePARIsUsed(buidlingId, clickedPAR.getParID(), newIsUsed);
        if (isUpdated) {
            Log.d("onItemClicked", "Updated isUsed in DB");
            parRecyclerViewAdapter.notifyItemChanged(position); // Odśwież element RecyclerView
        } else {
            Toast.makeText(this, "Aktualizowanie pozycji zakończone niepowodzeniem!", Toast.LENGTH_SHORT).show();
        }
    }

}