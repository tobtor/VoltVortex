package com.example.voltvortex.RecyclerViewAdapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.voltvortex.R;
import com.example.voltvortex.DataBaseHelper.MyDatabaseHelper;
import com.example.voltvortex.Models.ContactPersonModel;

import java.util.ArrayList;
import java.util.List;

public class ContactPersonRecyclerViewAdapter extends RecyclerView.Adapter<ContactPersonRecyclerViewAdapter.ContactPersonViewHolder> implements Filterable {
    private List<ContactPersonModel> contactPersonList;
    private List<ContactPersonModel> contactPersonListFull;
    private MyDatabaseHelper myDatabaseHelper;
    private OnItemClickListener listener;
    private int selectedPosition = RecyclerView.NO_POSITION;
    private int previousSelectedPosition = RecyclerView.NO_POSITION;

    public interface OnItemClickListener {
        void onItemClick(int contactPersonID);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public ContactPersonRecyclerViewAdapter(List<ContactPersonModel> contactPersonList, MyDatabaseHelper myDatabaseHelper) {
        this.contactPersonList = contactPersonList;
        this.contactPersonListFull = new ArrayList<>(contactPersonList);
        this.myDatabaseHelper = myDatabaseHelper;
    }

    @NonNull
    @Override
    public ContactPersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_listview_contact_person_search,
                parent, false);
        return new ContactPersonViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactPersonViewHolder holder, int position) {
        ContactPersonModel contactPersonModel = contactPersonList.get(position);
        holder.textViewContactPersonId.setText(String.valueOf(contactPersonModel.getContactPersonID()));
        holder.textViewContactPersonName.setText(contactPersonModel.getName());
        holder.textViewPhone.setText(contactPersonModel.getPhone());

        if (selectedPosition == position) {
            holder.cardView.setCardBackgroundColor(holder.itemView.getContext().getResources().getColor(R.color.buttonHighLightColor));
        } else {
            holder.cardView.setCardBackgroundColor(holder.itemView.getContext().getResources().getColor(R.color.buttonNormalColor));
        }
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

    public class ContactPersonViewHolder extends RecyclerView.ViewHolder {
        TextView textViewContactPersonId, textViewContactPersonName, textViewPhone;
        CardView cardView;

        public ContactPersonViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            textViewContactPersonId = itemView.findViewById(R.id.listViewTextContactPersonID);
            textViewContactPersonName = itemView.findViewById(R.id.listViewTextContactPersonName);
            textViewPhone = itemView.findViewById(R.id.listViewTextContactPersonPhone);
            cardView = itemView.findViewById(R.id.cardViewContactPerson);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            previousSelectedPosition = selectedPosition;
                            if (selectedPosition == position) {
                                selectedPosition = RecyclerView.NO_POSITION;
                                listener.onItemClick(0);
                            } else {
                                selectedPosition = position;
                                listener.onItemClick(Integer.parseInt(textViewContactPersonId.getText().toString()));
                            }
                            notifyItemChanged(previousSelectedPosition);
                            notifyItemChanged(selectedPosition);
                        }
                    }
                }
            });
        }
    }
}
