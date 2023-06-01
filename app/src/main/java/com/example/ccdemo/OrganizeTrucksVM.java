package com.example.ccdemo;

import android.util.Log;

import androidx.lifecycle.ViewModel;


import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class OrganizeTrucksVM extends ViewModel {

    private OrganizeTrucksFBRepo organizeTrucksFBRepo;

    @Inject
    public OrganizeTrucksVM(OrganizeTrucksFBRepo organizeTrucksFBRepo) {
        this.organizeTrucksFBRepo = organizeTrucksFBRepo;
    }
    public void getAvailableDrivers ( OrganizeTrucksInterface organizeTrucksInterface, String organizationName){
        Log.d("OrganizeTrucksVM", "getAvailableDrivers() called");
        organizeTrucksFBRepo.getAvailableDrivers(organizeTrucksInterface,organizationName);
    }
    public boolean reassignGET (String driverName) {
        Log.d("OrganizeTrucksVM", "reassignGET() called");
        return organizeTrucksFBRepo.reassignGET(driverName);
    }
    public boolean reassignDROP (String driverName) {
        Log.d("OrganizeTrucksVM", "reassignDrop() called");
        return organizeTrucksFBRepo.reassignDrop(driverName);
    }


}
