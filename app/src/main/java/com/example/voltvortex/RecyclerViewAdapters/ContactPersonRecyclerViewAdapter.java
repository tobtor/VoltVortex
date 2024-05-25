package com.example.voltvortex.RecyclerViewAdapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.voltvortex.DataBaseHelper.MyDatabaseHelper;
import com.example.voltvortex.Models.ContactPersonModel;
import com.example.voltvortex.R;
import java.util.ArrayList;
import java.util.List;

public class ContactPersonRecyclerViewAdapter extends RecyclerView.Adapter<ContactPersonRecyclerViewAdapter.ContactPersonViewHolder> implements Filterable {

    private List<ContactPersonModel> contactPersonList;
    private List<ContactPersonModel> contactPersonListFull;
    private MyDatabaseHelper myDatabaseHelper;

    public ContactPersonRecyclerViewAdapter(List<ContactPersonModel> contactPersonList,
                                            MyDatabaseHelper myDatabaseHelper) {
        this.contactPersonList = contactPersonList;
        this.contactPersonListFull = new ArrayList<>(contactPersonList);
        this.myDatabaseHelper = myDatabaseHelper;
    }

    @NonNull
    @Override
    public ContactPersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_listview_contact_person_search,
                parent, false);
        return new ContactPersonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactPersonViewHolder holder, int position) {
        ContactPersonModel contactPersonModel = contactPersonList.get(position);
        holder.textViewContactPersonId.setText(String.valueOf(contactPersonModel.getContactPersonID()));;
        holder.textViewContactPersonName.setText(contactPersonModel.getName());
        holder.textViewPhone.setText(contactPersonModel.getPhone());
    }




    @Override
    public int getItemCount() {
        return contactPersonList.size();
    }

    @Override
    public Filter getFilter() {
        return contactPersonFilter;
    }

    private Filter contactPersonFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<ContactPersonModel> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(contactPersonListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (ContactPersonModel item : contactPersonListFull) {
                    if (item.getName().toLowerCase().contains(filterPattern) ||
                            String.valueOf(item.getContactPersonID()).contains(filterPattern) ||
                            item.getPhone().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            contactPersonList.clear();
            contactPersonList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public static class ContactPersonViewHolder extends RecyclerView.ViewHolder {
        TextView textViewContactPersonId, textViewContactPersonName, textViewPhone;

        public ContactPersonViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewContactPersonId = itemView.findViewById(R.id.listViewTextContactPersonID);
            textViewContactPersonName = itemView.findViewById(R.id.listViewTextContactPersonName);
            textViewPhone = itemView.findViewById(R.id.listViewTextContactPersonPhone);
        }
    }
}
