package com.example.voltvortex.RecyclerViewAdapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.voltvortex.DataBaseHelper.MyDatabaseHelper;
import com.example.voltvortex.Intefraces.RecyclerViewInterface;
import com.example.voltvortex.Models.ProjectModel;
import com.example.voltvortex.R;

import java.util.List;

public class ProjectRecyclerViewAdapter extends RecyclerView.Adapter<ProjectRecyclerViewAdapter.ProjectViewHolder> {

    private List<ProjectModel> projectList;
    private MyDatabaseHelper myDatabaseHelper;
    private boolean deleteMode = false;
    private final RecyclerViewInterface recyclerViewInterface;

    public ProjectRecyclerViewAdapter(List<ProjectModel> projectList,
                                      MyDatabaseHelper myDatabaseHelper,
                                      RecyclerViewInterface recyclerViewInterface) {
        this.projectList = projectList;
        this.myDatabaseHelper = myDatabaseHelper;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    public void setDeleteMode(boolean deleteMode) {
        this.deleteMode = deleteMode;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_recyclerview_main_activity, viewGroup, false);
        return new ProjectViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectViewHolder holder,
                                 @SuppressLint("RecyclerView") int position) {
        ProjectModel projectModel = projectList.get(position);
        holder.textViewName.setText(projectModel.getProjectName());
        holder.textViewId.setText(String.valueOf(projectModel.getProjectID()));

        holder.itemView.setOnClickListener(v -> {
            if (deleteMode) {
                myDatabaseHelper.deleteProject(projectModel.getProjectID());
                projectList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, projectList.size());
                Toast.makeText(holder.itemView.getContext(), "Deleted " + projectModel.toString(), Toast.LENGTH_SHORT).show();
            } else {
                recyclerViewInterface.onItemClicked(position);
            }
        });

        holder.itemView.setOnLongClickListener(v -> {
            new AlertDialog.Builder(holder.itemView.getContext())
                    .setTitle("Delete Project")
                    .setMessage("Are you sure you want to delete this project?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        myDatabaseHelper.deleteProject(projectModel.getProjectID());
                        projectList.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, projectList.size());
                        Toast.makeText(holder.itemView.getContext(), "Deleted " + projectModel.toString(), Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("No", null)
                    .show();
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return projectList.size();
    }

    public ProjectModel getProjectAt(int position) {
        return projectList.get(position);
    }

    public static class ProjectViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName, textViewId;

        public ProjectViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.listViewTextProjectName);
            textViewId = itemView.findViewById(R.id.textAddingDate);
        }
    }
}