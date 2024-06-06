package com.example.voltvortex.RecyclerViewAdapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.voltvortex.Models.RoomModel;
import com.example.voltvortex.R;
import com.example.voltvortex.Activities.ZSActivity; // Importuj ZSActivity
import java.util.List;

public class RoomRecyclerViewAdapter extends RecyclerView.Adapter<RoomRecyclerViewAdapter.ViewHolder> {

    private List<RoomModel> roomData;
    private Context context;
    private int buildingId;
    private int floorId;

    public RoomRecyclerViewAdapter(Context context, List<RoomModel> roomData, int buildingId, int floorId) {
        this.context = context;
        this.roomData = roomData;
        this.buildingId = buildingId;
        this.floorId = floorId;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_recyclerview_room, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RoomModel room = roomData.get(position);
        holder.roomTextView.setText(room.getRoom());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ZSActivity.class);
            intent.putExtra("BUILDING_ID", buildingId);
            intent.putExtra("FLOOR_ID", floorId);
            intent.putExtra("ROOM_ID", room.getRoomId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return roomData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView roomTextView;

        ViewHolder(View itemView) {
            super(itemView);
            roomTextView = itemView.findViewById(R.id.roomTextView);
        }
    }
}
