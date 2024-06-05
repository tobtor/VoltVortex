package com.example.voltvortex.RecyclerViewAdapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.voltvortex.DataBaseHelper.MyDatabaseHelper;
import com.example.voltvortex.Intefraces.RecyclerViewInterface;
import com.example.voltvortex.Models.PARModel;
import com.example.voltvortex.R;
import java.util.List;

public class PARRecyclerViewAdapter extends RecyclerView.Adapter<PARRecyclerViewAdapter.PARViewHolder> {

    private List<PARModel> parList;
    private MyDatabaseHelper myDatabaseHelper;
    private final RecyclerViewInterface recyclerViewInterface;
    private Context context;
    private int buildingID;

    public PARRecyclerViewAdapter(List<PARModel> parList, MyDatabaseHelper myDatabaseHelper, RecyclerViewInterface recyclerViewInterface, int buildingID, Context context) {
        this.parList = parList;
        this.myDatabaseHelper = myDatabaseHelper;
        this.recyclerViewInterface = recyclerViewInterface;
        this.buildingID = buildingID;
        this.context = context;
    }

    @NonNull
    @Override
    public PARViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_recyclerview_par, viewGroup, false);
        return new PARViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull PARViewHolder holder, @SuppressLint("RecyclerView") int position) {
        PARModel parModel = parList.get(position);
        holder.textViewPAR.setText(parModel.getContent());
        updateLinearLayoutBackground(holder.linearLayout, parModel.getIsUsed());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newIsUsed = parModel.getIsUsed() == 1 ? 0 : 1;
                parModel.setIsUsed(newIsUsed);
                boolean isUpdated = myDatabaseHelper.updatePARIsUsed(buildingID, parModel.getParID(), newIsUsed);
                if (isUpdated) {
                    updateLinearLayoutBackground(holder.linearLayout, newIsUsed);
                    notifyItemChanged(position); // Odśwież element RecyclerView
                } else {
                    Toast.makeText(context, "Failed to update PAR", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return parList.size();
    }

    private void updateLinearLayoutBackground(LinearLayout linearLayout, int isUsed) {
        int backgroundResource = isUsed == 1 ? R.drawable.radius_normal : R.drawable.radius_highlight;
        linearLayout.setBackgroundResource(backgroundResource);
    }

    public PARModel getPARAt(int position) {
        return parList.get(position);
    }

    public class PARViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewPAR;
        public LinearLayout linearLayout;

        public PARViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            textViewPAR = itemView.findViewById(R.id.textViewPPAR);
            linearLayout = itemView.findViewById(R.id.LinearLayoutPAR);

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