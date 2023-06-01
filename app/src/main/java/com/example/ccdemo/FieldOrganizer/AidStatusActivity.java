package com.example.catastrophecompass.UILayer.FieldOrganizer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.catastrophecompass.R;

public class AidStatusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aid_status); // Replace with your layout filename

        // Load the AidStatsFragment into the first FrameLayout
        AidStatusFragment aidStatsFragment = new AidStatusFragment();
        FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
        transaction1.replace(R.id.ait_stats, aidStatsFragment);
        transaction1.commit();

        // Load the AidAmountFragment into the second FrameLayout
        AidAmountFragment aidAmountFragment = new AidAmountFragment();
        FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();
        transaction2.replace(R.id.aid_amount_status, aidAmountFragment);
        transaction2.commit();


    }
}