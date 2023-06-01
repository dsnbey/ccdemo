package com.example.ccdemo.FieldOrganizer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ccdemo.Model.NeedItem;
import com.example.ccdemo.R;

import java.util.List;

public class NeedItemAdapter2 extends RecyclerView.Adapter<NeedItemAdapter2.NeedItemViewHolder> {

    private List<NeedItem> needItems;

    public NeedItemAdapter2(List<NeedItem> needItems) {
        this.needItems = needItems;
    }

    @NonNull
    @Override
    public NeedItemAdapter2.NeedItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.update_aid_item, parent, false);
        return new NeedItemAdapter2.NeedItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NeedItemAdapter2.NeedItemViewHolder holder, int position) {
        NeedItem needItem = needItems.get(position);
        holder.typeOfNeed.setText(needItem.getTypeOfNeed());
        holder.urgency.setText(needItem.getUrgency());
    }

    @Override
    public int getItemCount() {
        return needItems.size();
    }

    public int getItem(int position){
        return Integer.parseInt(needItems.get(position).getUrgency());
    }
    public static class NeedItemViewHolder extends RecyclerView.ViewHolder {

        TextView typeOfNeed;
        EditText urgency;

        public NeedItemViewHolder(@NonNull View itemView) {
            super(itemView);
            typeOfNeed = itemView.findViewById(R.id.updateAidTextView);
            urgency = itemView.findViewById(R.id.updateAidEditText);
        }
    }
}