package com.example.ccdemo;


import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ccdemo.Model.DriverItem;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class OrganizeTrucksActivity extends AppCompatActivity implements OrganizeTrucksInterface{

    private RecyclerView recyclerView;
    private TruckAdapter truckAdapter;
    public OrganizeTrucksVM vm;
    private List<DriverItem> truckList;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate the layout for this activity
        setContentView(R.layout.activity_organize_trucks);

        vm = new ViewModelProvider(this).get(OrganizeTrucksVM.class);
        vm.getAvailableDrivers(this, HQOrganizerCommon.organizationName);
        // TODO common
        recyclerView = findViewById(R.id.rec_organize_truck_act);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        truckList = new ArrayList<>();
        truckAdapter = new TruckAdapter(this, truckList, this);
        recyclerView.setAdapter(truckAdapter);
    }

    @Override
    public void setDisplay(List<DriverItem> list) {
        // Initialize the RecyclerView, its layout manager and the adapter
        truckList = list;

        //truckList.clear();
        //truckList =new ArrayList<>(list);
        truckAdapter.notifyDataSetChanged(); // TODO test notifyDataSetChanged()
    }

    @Override
    public void warnUser() {

    }

    // You can add more methods for managing the Truck data here if needed
}
