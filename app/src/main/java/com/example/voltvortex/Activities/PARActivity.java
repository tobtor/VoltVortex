package com.example.voltvortex.Activities;

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

public class PARActivity extends AppCompatActivity implements RecyclerViewInterface {

    int buidlingId;
    RecyclerView recyclerViewPAR;
    MyDatabaseHelper myDatabaseHelper;
    PARRecyclerViewAdapter parRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_par_activity);
        buidlingId = getIntent().getExtras().getInt("BUILDING_ID");
        recyclerViewPAR = findViewById(R.id.recyclerViewPAR);

        myDatabaseHelper = new MyDatabaseHelper(this);

        recyclerViewPAR.setLayoutManager(new LinearLayoutManager(this));

        getPARList(myDatabaseHelper, buidlingId);
    }

    private void getPARList(MyDatabaseHelper myDatabaseHelper, int buidlingId) {
        List<PARModel> pparList = myDatabaseHelper.viewPARList(buidlingId);
        if (pparList.isEmpty()) {
            Toast.makeText(this, "Nie udało się uzyskać listy PPAR!", Toast.LENGTH_SHORT).show();
        } else {
            parRecyclerViewAdapter = new PARRecyclerViewAdapter(pparList,
                    myDatabaseHelper, this);
            recyclerViewPAR.setAdapter(parRecyclerViewAdapter);
        }
    }

    @Override
    public void onItemClicked(int position) {

    }
}