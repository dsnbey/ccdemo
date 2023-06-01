package com.example.ccdemo.FieldOrganizer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ccdemo.Model.InventoryList;
import com.example.ccdemo.Model.NeedItem;
import com.example.ccdemo.R;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class UpdateAidStatusActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private NeedItemAdapter2 adapter;
    private Button button;
    private UpdateAidStatusVM vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_aid_status);
        recyclerView = findViewById(R.id.updateAidRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        button = findViewById(R.id.doneButton);
        vm = new ViewModelProvider(this).get(UpdateAidStatusVM.class);

        preDisplay(FieldOrganizerCommon.aidStatus);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InventoryList newList = new InventoryList(0, adapter.getItem(0), adapter.getItem(1), adapter.getItem(2), adapter.getItem(3), adapter.getItem(4), adapter.getItem(5), adapter.getItem(6), adapter.getItem(7));
                if (vm.updateAidStatusInfo(newList))
                    directAidFragment();
                else
                    warnUser();
            }
        });

    }

    public void preDisplay(InventoryList inventoryList){
        List<NeedItem> items = new ArrayList<>();
        items.add(new NeedItem("Food", inventoryList.getFood()+""));
        items.add(new NeedItem("Heater", ""+inventoryList.getHeater()));
        items.add(new NeedItem("Man Cloth", ""+inventoryList.getManCloth()));
        items.add(new NeedItem("Woman Cloth", ""+inventoryList.getWomanCloth()));
        items.add(new NeedItem("Hygiene", ""+inventoryList.getHygene()));
        items.add(new NeedItem("Kitchen Material", ""+inventoryList.getKitchenMaterial()));
        items.add(new NeedItem("Powerbank", ""+inventoryList.getPowerbank()));
        adapter = new NeedItemAdapter2(items);
        recyclerView.setAdapter(adapter);
    }

    public void directAidFragment(){
        Intent intent = new Intent(this, AidStatusActivity.class);
        startActivity(intent);
    }
    public void warnUser(){

    }
}