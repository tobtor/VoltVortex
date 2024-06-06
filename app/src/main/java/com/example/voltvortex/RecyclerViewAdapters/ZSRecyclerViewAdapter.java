package com.example.voltvortex.RecyclerViewAdapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.voltvortex.DataBaseHelper.MyDatabaseHelper;
import com.example.voltvortex.Models.ZSModel;
import com.example.voltvortex.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ZSRecyclerViewAdapter extends RecyclerView.Adapter<ZSRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "ZSRecyclerViewAdapter";
    private List<ZSModel> zsPoints;
    private Context context;
    private MyDatabaseHelper dbHelper;
    private Map<Integer, String> componentNamesMap;

    public ZSRecyclerViewAdapter(Context context, List<ZSModel> zsPoints) {
        this.context = context;
        this.zsPoints = zsPoints;
        this.dbHelper = new MyDatabaseHelper(context);
        this.componentNamesMap = new HashMap<>();
    }

    public void updateData(List<ZSModel> newPoints) {
        this.zsPoints = newPoints;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_zs_point, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ZSModel zsPoint = zsPoints.get(position);

        // Pobierz nazwę komponentu, jeśli nie jest jeszcze pobrana
        int measuredComponentID = zsPoint.getMeasuredComponentID();
        String componentName = componentNamesMap.get(measuredComponentID);

        if (componentName == null) {
            try {
                componentName = dbHelper.getComponentName(measuredComponentID);
                componentNamesMap.put(measuredComponentID, componentName);
            } catch (Exception e) {
                Log.e(TAG, "Error while fetching component name for ID: " + measuredComponentID, e);
                componentName = "Unknown"; // Wartość domyślna w przypadku błędu
            }
        }

        Log.d(TAG, "onBindViewHolder: MeasuredComponentID=" + measuredComponentID + ", ComponentName=" + componentName);

        holder.textViewPointName.setText(componentName);

        // Ustaw widoczność TextView w zależności od wartości w modelu
        holder.textViewBZ.setVisibility(zsPoint.isBZ() ? View.VISIBLE : View.GONE);
        holder.textViewBPE.setVisibility(zsPoint.isBPE() ? View.VISIBLE : View.GONE);
        holder.textViewBK.setVisibility(zsPoint.isBK() ? View.VISIBLE : View.GONE);
        holder.textViewBKLAPKI.setVisibility(zsPoint.isBKLAPKI() ? View.VISIBLE : View.GONE);
        holder.textViewWYRW.setVisibility(zsPoint.isWYRW() ? View.VISIBLE : View.GONE);
        holder.textView2PRZEW.setVisibility(zsPoint.isIs2PRZEW() ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return zsPoints.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewPointName;
        TextView textViewBZ;
        TextView textViewBPE;
        TextView textViewBK;
        TextView textViewBKLAPKI;
        TextView textViewWYRW;
        TextView textView2PRZEW;

        ViewHolder(View itemView) {
            super(itemView);
            textViewPointName = itemView.findViewById(R.id.textViewPointName);
            textViewBZ = itemView.findViewById(R.id.textViewBZ);
            textViewBPE = itemView.findViewById(R.id.textViewBPE);
            textViewBK = itemView.findViewById(R.id.textViewBK);
            textViewBKLAPKI = itemView.findViewById(R.id.textViewBKLAPKI);
            textViewWYRW = itemView.findViewById(R.id.textViewWYRW);
            textView2PRZEW = itemView.findViewById(R.id.textView2PRZEW);
        }
    }
}