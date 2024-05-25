package com.example.voltvortex.RecyclerViewAdapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.voltvortex.DataBaseHelper.MyDatabaseHelper;
import com.example.voltvortex.Intefraces.ProjectRecyclerViewInterface;
import com.example.voltvortex.Models.ProjectModel;
import com.example.voltvortex.R;
import java.util.List;

public class ProjectRecyclerViewAdapter
        extends RecyclerView.Adapter<ProjectRecyclerViewAdapter.ProjectViewHolder> {

    private List<ProjectModel> projectList;
    private MyDatabaseHelper myDatabaseHelper;
    private boolean deleteMode = false;
    private final ProjectRecyclerViewInterface projectRecyclerViewInterface;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int IdOfProject);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public ProjectRecyclerViewAdapter(List<ProjectModel> projectList,
                                      MyDatabaseHelper myDatabaseHelper,
                                      ProjectRecyclerViewInterface projectRecyclerViewInterface) {
        this.projectList = projectList;
        this.myDatabaseHelper = myDatabaseHelper;
        this.projectRecyclerViewInterface = projectRecyclerViewInterface;
    }

    public void setDeleteMode(boolean deleteMode) {
        this.deleteMode = deleteMode;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_listview_main_activity, parent, false);
        return new ProjectViewHolder(view, projectRecyclerViewInterface, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ProjectModel projectModel = projectList.get(position);
        holder.textViewName.setText(projectModel.getProjectName());
        holder.textViewId.setText(String.valueOf(projectModel.getProjectID()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (deleteMode) {
                    myDatabaseHelper.deleteProject(projectModel);
                    projectList.remove(position);
                    notifyItemRemoved(position);
                    Toast.makeText(holder.itemView.getContext(), "Deleted " + projectModel.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return projectList.size();
    }

    public static class ProjectViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName, textViewId;

        public ProjectViewHolder(@NonNull View itemView, ProjectRecyclerViewInterface projectRecyclerViewInterface,final OnItemClickListener listener) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.listViewTextProjectName);
            textViewId = itemView.findViewById(R.id.textAddingDate);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (projectRecyclerViewInterface != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(Integer.parseInt(textViewId.getText().toString()));
                        }
                    }
                }
            });
        }
    }
}