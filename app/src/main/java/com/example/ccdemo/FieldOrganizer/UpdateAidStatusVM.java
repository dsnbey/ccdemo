package com.example.catastrophecompass.UILayer.FieldOrganizer;
import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.catastrophecompass.DataLayer.Model.InventoryList;
import com.example.catastrophecompass.DomainLayer.Domain.FieldOrganizationDomain;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class UpdateAidStatusVM extends ViewModel {

    private FieldOrganizationDomain domain;

    @Inject
    public UpdateAidStatusVM(FieldOrganizationDomain domain) {
        this.domain = domain;
    }

    public boolean updateAidStatusInfo(InventoryList inventoryList) {
        Log.d("UpdateAidStatusVM", "updateAidStatusInfo() called");
        return domain.updateAidStatusInfo(inventoryList);
    }


}