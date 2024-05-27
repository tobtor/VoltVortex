package com.example.voltvortex.RecyclerViewAdapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.voltvortex.DataBaseHelper.MyDatabaseHelper;
import com.example.voltvortex.Models.PPARModel;
import com.example.voltvortex.R;
import com.example.voltvortex.Intefraces.RecyclerViewInterface;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PPARRecyclerViewAdapter
        extends RecyclerView.Adapter<PPARRecyclerViewAdapter.PPARViewHolder> {

    private List<PPARModel> pparList;
    private MyDatabaseHelper myDatabaseHelper;
    private final RecyclerViewInterface recyclerViewInterface;

    public PPARRecyclerViewAdapter(List<PPARModel> pparList,
                                   MyDatabaseHelper myDatabaseHelper,
                                   RecyclerViewInterface recyclerViewInterface) {
        this.pparList = pparList;
        this.myDatabaseHelper = myDatabaseHelper;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @NotNull
    @Override
    public PPARViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_recyclerview_par, viewGroup, false);
        return new PPARViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PPARViewHolder holder, int position) {
        PPARModel pparModel = pparList.get(position);
        holder.textViewPPAR.setText(pparModel.getContent());
    }

    @Override
    public int getItemCount() {
        return pparList.size();
    }

    public PPARModel getPPARAt(int position) {
        return pparList.get(position);
    }

    public class PPARViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewPPAR;

        public PPARViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            textViewPPAR= itemView.findViewById(R.id.textViewPPAR);

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

