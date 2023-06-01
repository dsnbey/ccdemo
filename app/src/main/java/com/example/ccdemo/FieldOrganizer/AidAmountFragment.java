package com.example.ccdemo.FieldOrganizer;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ccdemo.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

public class AidAmountFragment extends Fragment implements ArrivingInterface {

    private BarChart barChart;
    private AidStatusVM vm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_aid_amount, container, false);
        barChart = view.findViewById(R.id.aid_amount_chart);
        vm = new ViewModelProvider(this).get(AidStatusVM.class);

        vm.getArrivingInfo(this);

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void setArrivingDisplay(InventoryList inventoryList) {
        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0, inventoryList.getFood()));
        entries.add(new BarEntry(1, inventoryList.getHeater()));
        entries.add(new BarEntry(2, inventoryList.getManCloth()));
        entries.add(new BarEntry(3, inventoryList.getWomanCloth()));
        entries.add(new BarEntry(4, inventoryList.getChildCloth()));
        entries.add(new BarEntry(5, inventoryList.getHygene()));
        entries.add(new BarEntry(6, inventoryList.getKitchenMaterial()));
        entries.add(new BarEntry(7, inventoryList.getPowerbank()));
        BarDataSet dataSet = new BarDataSet(entries, "Aid Status");
        BarData barData = new BarData(dataSet);

        barChart.setData(barData);
        barChart.getDescription().setEnabled(false);
        barChart.getLegend().setEnabled(false);
        barChart.setFitBars(true);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(getXAxisLabels()));

        YAxis yAxisLeft = barChart.getAxisLeft();
        yAxisLeft.setDrawGridLines(false);

        YAxis yAxisRight = barChart.getAxisRight();
        yAxisRight.setDrawGridLines(false);

        barChart.invalidate();
    }

    @Override
    public void warnUser() {

    }

    private String[] getXAxisLabels(){
        return new String[]{"Food", "Heater", "Man Cloth", "Woman Cloth", "Child Cloth", "Hygiene", "Kitchen Material", "Powerbank"};
    }
}