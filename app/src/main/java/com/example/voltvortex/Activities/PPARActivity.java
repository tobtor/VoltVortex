package com.example.voltvortex.Activities;

import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.voltvortex.DataBaseHelper.MyDatabaseHelper;
import com.example.voltvortex.Intefraces.RecyclerViewInterface;
import com.example.voltvortex.Models.PPARModel;
import com.example.voltvortex.R;
import com.example.voltvortex.RecyclerViewAdapters.PPARRecyclerViewAdapter;

import java.util.List;

public class PPARActivity extends AppCompatActivity implements RecyclerViewInterface {

    int buidlingId, pparId;
    RecyclerView recyclerViewPPAR, recyclerViewPAR;
    MyDatabaseHelper myDatabaseHelper;
    PPARRecyclerViewAdapter pparRecyclerViewAdapter;
    //PARRecyclerViewAdapter parRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_par_activity);
        buidlingId = getIntent().getExtras().getInt("BUILDING_ID");
        recyclerViewPPAR = findViewById(R.id.recyclerViewPPAR);
        recyclerViewPAR = findViewById(R.id.recyclerViewPAR);

        myDatabaseHelper = new MyDatabaseHelper(this);

        recyclerViewPPAR.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewPAR.setLayoutManager(new LinearLayoutManager(this));

        getPPARList(myDatabaseHelper);
        //getPARList(myDatabaseHelper);

    }

    private void getPPARList(MyDatabaseHelper myDatabaseHelper) {
        List<PPARModel> pparList = myDatabaseHelper.viewPPARList();
        if (pparList.isEmpty()) {
            Toast.makeText(this, "Nie udało się uzyskać listy PPAR!", Toast.LENGTH_SHORT).show();
        } else {
            pparRecyclerViewAdapter = new PPARRecyclerViewAdapter(pparList,
                    myDatabaseHelper, this);
            recyclerViewPPAR.setAdapter(pparRecyclerViewAdapter);
        }
    }

    private void getPARList (MyDatabaseHelper myDatabaseHelper){

    }

    @Override
    public void onItemClicked(int position) {

    }
}