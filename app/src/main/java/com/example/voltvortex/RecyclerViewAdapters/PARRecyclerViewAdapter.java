package com.example.voltvortex.RecyclerViewAdapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.voltvortex.DataBaseHelper.MyDatabaseHelper;
import com.example.voltvortex.Models.PARModel;
import com.example.voltvortex.R;
import com.example.voltvortex.Intefraces.RecyclerViewInterface;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PARRecyclerViewAdapter
        extends RecyclerView.Adapter<PARRecyclerViewAdapter.PARViewHolder> {

    private List<PARModel> parList;
    private MyDatabaseHelper myDatabaseHelper;
    private final RecyclerViewInterface recyclerViewInterface;

    public PARRecyclerViewAdapter(List<PARModel> parList,
                                  MyDatabaseHelper myDatabaseHelper,
                                  RecyclerViewInterface recyclerViewInterface) {
        this.parList = parList;
        this.myDatabaseHelper = myDatabaseHelper;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @NotNull
    @Override
    public PARViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_recyclerview_par, viewGroup, false);
        return new PARViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PARViewHolder holder, int position) {
        PARModel parModel = parList.get(position);
        holder.textViewPAR.setText(parModel.getContent());
    }

    @Override
    public int getItemCount() {
        return parList.size();
    }

    public PARModel getPARAt(int position) {
        return parList.get(position);
    }

    public class PARViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewPAR;

        public PARViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            textViewPAR= itemView.findViewById(R.id.textViewPPAR);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (recyclerViewInterface != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            recyclerViewInterface.onItemClicked(position);
                        }
                    }
                }
            });
        }
    }
}

