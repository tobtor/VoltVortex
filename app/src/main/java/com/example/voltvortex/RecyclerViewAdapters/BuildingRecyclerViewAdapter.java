package com.example.voltvortex.RecyclerViewAdapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.voltvortex.DataBaseHelper.MyDatabaseHelper;
import com.example.voltvortex.Intefraces.RecyclerViewInterface;
import com.example.voltvortex.Models.BuildingModel;
import com.example.voltvortex.R;
import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class BuildingRecyclerViewAdapter
        extends RecyclerView.Adapter<BuildingRecyclerViewAdapter.BuildingViewHolder>{

    private List<BuildingModel> buildingList;
    private MyDatabaseHelper myDatabaseHelper;
    private final RecyclerViewInterface recyclerViewInterface;

    public BuildingRecyclerViewAdapter(List<BuildingModel> buildingList,
                                       MyDatabaseHelper myDatabaseHelper,
                                       RecyclerViewInterface recyclerViewInterface) {
        this.buildingList = buildingList;
        this.myDatabaseHelper = myDatabaseHelper;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @NotNull
    @Override
    public BuildingViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_recyclerview_project_activity, viewGroup, false);
        return new BuildingViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull BuildingViewHolder holder,
                                 @SuppressLint("RecyclerView") int position) {
        BuildingModel buildingModel = buildingList.get(position);

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy, EEEE", Locale.getDefault());
        String formattedDate = sdf.format(buildingModel.getDateOfMeasurements());

        holder.textViewBuildingName.setText(buildingModel.getBuildingName());
        holder.textViewDate.setText(formattedDate);

        /*holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    myDatabaseHelper.deleteBuilding(buildingModel);
                    buildingList.remove(position);
                    notifyItemRemoved(position);
                    Toast.makeText(holder.itemView.getContext(), "Deleted " + buildingModel.toString(), Toast.LENGTH_SHORT).show();
                } else {
                    recyclerViewInterface.onItemClicked(position);
                }
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return buildingList.size();
    }

    public BuildingModel getBuildingAt(int position) {
        return buildingList.get(position);
    }

    public class BuildingViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewBuildingName, textViewDate;

        public BuildingViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            textViewBuildingName = itemView.findViewById(R.id.textViewBuildingName);
            textViewDate = itemView.findViewById(R.id.textViewDate);

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
