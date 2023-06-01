package com.example.ccdemo.FieldOrganizer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.catastrophecompass.DataLayer.Model.InventoryList;
import com.example.catastrophecompass.DomainLayer.Common.FieldOrganizerCommon;
import com.example.catastrophecompass.R;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;



import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.github.mikephil.charting.charts.HorizontalBarChart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import com.github.mikephil.charting.charts.HorizontalBarChart;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

public class AidStatusFragment extends Fragment implements InventoryInterface {

    private HorizontalBarChart aidStatsChart;
    private AidStatusVM vm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_aid_stats, container, false);
        vm = new ViewModelProvider(this).get(AidStatusVM.class);
        vm.getInventoryInfo(this);

        aidStatsChart = view.findViewById(R.id.aid_stats_chart);
        Button updateButton = view.findViewById(R.id.button);


        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start UpdateAidStatusActivity
                Intent intent = new Intent(getActivity(), UpdateAidStatusActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void setDisplay(InventoryList inventoryList) {
        FieldOrganizerCommon.aidStatus = inventoryList;
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

        aidStatsChart.setData(barData);
        aidStatsChart.getDescription().setEnabled(false);
        aidStatsChart.getLegend().setEnabled(false);
        aidStatsChart.setFitBars(true);

        XAxis xAxis = aidStatsChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(getXAxisLabels()));

        YAxis yAxisLeft = aidStatsChart.getAxisLeft();
        yAxisLeft.setDrawGridLines(false);

        YAxis yAxisRight = aidStatsChart.getAxisRight();
        yAxisRight.setDrawGridLines(false);

        aidStatsChart.invalidate();
    }


    @Override
    public void warnUser() {

    }

    private String[] getXAxisLabels(){
        return new String[]{"Food", "Heater", "Man Cloth", "Woman Cloth", "Child Cloth", "Hygiene", "Kitchen Material", "Powerbank"};
    }
}