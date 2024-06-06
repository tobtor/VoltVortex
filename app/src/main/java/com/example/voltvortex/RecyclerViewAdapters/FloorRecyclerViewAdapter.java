package com.example.voltvortex.RecyclerViewAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.voltvortex.DataBaseHelper.MyDatabaseHelper;
import com.example.voltvortex.Models.FloorModel;
import com.example.voltvortex.Models.RoomModel;
import com.example.voltvortex.R;
import java.util.List;

public class FloorRecyclerViewAdapter extends RecyclerView.Adapter<FloorRecyclerViewAdapter.ViewHolder> {

    private List<FloorModel> floorData;
    private Context context;
    private int buildingId;
    private int expandedPosition = -1; // Dodaj zmienną do śledzenia rozwiniętego elementu

    public FloorRecyclerViewAdapter(Context context, List<FloorModel> floorData, int buildingId) {
        this.context = context;
        this.floorData = floorData;
        this.buildingId = buildingId;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_recyclerview_floor, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        FloorModel floor = floorData.get(position);
        holder.floorTextView.setText(floor.getFloor());

        MyDatabaseHelper dbHelper = new MyDatabaseHelper(context);
        List<RoomModel> rooms = dbHelper.getRoomsForFloor(buildingId, floor.getFloorId());
        RoomRecyclerViewAdapter roomAdapter = new RoomRecyclerViewAdapter(context, rooms, buildingId, floor.getFloorId());

        holder.roomRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        holder.roomRecyclerView.setAdapter(roomAdapter);

        final boolean isExpanded = position == expandedPosition;
        holder.roomRecyclerView.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
        holder.itemView.setActivated(isExpanded);

        holder.itemView.setOnClickListener(v -> {
            expandedPosition = isExpanded ? -1 : position;
            notifyItemChanged(position); // Zaktualizuj obecny element
            notifyDataSetChanged(); // Zaktualizuj całą listę, aby zwinąć inne rozwinięte elementy
        });
    }

    @Override
    public int getItemCount() {
        return floorData.size();
    }

    public void updateFloors(List<FloorModel> newFloors) {
        this.floorData = newFloors;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView floorTextView;
        RecyclerView roomRecyclerView;
        LinearLayout floorLayout;

        ViewHolder(View itemView) {
            super(itemView);
            floorTextView = itemView.findViewById(R.id.floorTextView);
            roomRecyclerView = itemView.findViewById(R.id.roomRecyclerView);
            floorLayout = itemView.findViewById(R.id.LinearLayoutFloor);
        }
    }
}
