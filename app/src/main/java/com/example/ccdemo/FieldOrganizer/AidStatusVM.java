package com.example.ccdemo.FieldOrganizer;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.catastrophecompass.DomainLayer.Domain.FieldOrganizationDomain;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class AidStatusVM extends ViewModel {

    private FieldOrganizationDomain domain;

    @Inject
    public AidStatusVM(FieldOrganizationDomain domain) {
        this.domain = domain;
    }


    public void getInventoryInfo(InventoryInterface inventoryInterface) {
        Log.d("AidStatusVM", "getInventoryInfo() called");
        domain.getInventoryInfo(inventoryInterface);
    }

    public void getArrivingInfo(ArrivingInterface arrivingInterface) {
        Log.d("AidStatusVM", "getArrivingInfo() called");
        domain.getArrivingInfo(arrivingInterface);
    }
}