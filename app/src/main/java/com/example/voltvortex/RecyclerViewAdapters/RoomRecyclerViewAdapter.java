package com.example.voltvortex.RecyclerViewAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.voltvortex.Models.RoomModel;
import com.example.voltvortex.R;
import java.util.List;

public class RoomRecyclerViewAdapter extends RecyclerView.Adapter<RoomRecyclerViewAdapter.ViewHolder> {

    private List<RoomModel> roomData;
    private Context context;

    public RoomRecyclerViewAdapter(Context context, List<RoomModel> roomData) {
        this.context = context;
        this.roomData = roomData;
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
