package com.example.ccdemo;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.recyclerview.widget.RecyclerView;


import com.example.ccdemo.Model.DriverItem;

import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;

public class TruckAdapter extends RecyclerView.Adapter<TruckAdapter.TruckViewHolder> {

    // Our list of trucks
    private List<DriverItem> truckList;

    // The context we'll use to start the AssignToGetActivity
    private Context context;
    private OrganizeTrucksActivity fragment;

    // The constructor for our TruckAdapter
    public TruckAdapter(Context context, List<DriverItem> truckList, OrganizeTrucksActivity fragment) {
        this.fragment = fragment;
        this.context = context;
        this.truckList = truckList;
    }

    @NonNull
    @Override
    public TruckViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // We inflate the item layout here and create a ViewHolder instance
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.organize_truck_item, parent, false);
        return new TruckViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TruckViewHolder holder, int position) {
        // We retrieve the corresponding truck from our list
        DriverItem truck = truckList.get(position);
        if(truck.getDriverStatus().equals("available")){
            holder.assignButton.setVisibility(View.VISIBLE);
            holder.dropButton.setVisibility(View.GONE);

        }else{
            holder.assignButton.setVisibility(View.GONE);
            holder.dropButton.setVisibility(View.VISIBLE);

        }



        // Now we set the onClickListener for the assign button in the ViewHolder
        holder.assignButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                if (truck.getDriverStatus().equals("available")) {
//                    Intent intent = new Intent(context, AssignToGetActivity.class);
//                    context.startActivity(intent);
//                }
//
//                else if (truck.getDriverStatus().equals("onWayToGet")) {
//                    boolean deletionStatus = fragment.vm.reassignGET(truck.getDriverName());
//
//                    if (deletionStatus) {
//                        Intent intent = new Intent(context, AssignToGetActivity.class);
//                        context.startActivity(intent);
//                    }
//                    else {
//                        fragment.warnUser();
//                    }
//                }
//
//                else if (truck.getDriverStatus().equals("getChecked")) {
//                    boolean status = fragment.vm.reassignDROP(truck.getDriverName());
//                    Log.d("TruckAdapter ", "onClick: reassigndrop status: " + status);
//                }

            }
        });

        // Add any additional binding code here (for other fields in your Truck class)
        holder.driverName.setText(truck.getDriverName());
        holder.truckSize.setText(truck.getDriverSize());
        holder.truckStatus.setText(truck.getDriverStatus());


    }

    @Override
    public int getItemCount() {
        // The size of the list will determine how many items are in the RecyclerView
        return truckList.size();
    }

    class TruckViewHolder extends RecyclerView.ViewHolder {

        Button assignButton;
        Button dropButton;
        EditText driverName;
        EditText truckSize;
        EditText truckStatus;

        TruckViewHolder(@NonNull View itemView) {
            super(itemView);

            // We find the button in the inflated layout
            assignButton = itemView.findViewById(R.id.btn_truckAssaign_or_tr_it);
            dropButton = itemView.findViewById(R.id.btn_truckDrop_or_tr_it);
            driverName = itemView.findViewById(R.id.edt_driverName_or_tr_it);
            truckSize = itemView.findViewById(R.id.edt_truckSize_or_tr_it);
            truckStatus = itemView.findViewById(R.id.edt_truckStatus_or_tr_it);


        }
    }
}