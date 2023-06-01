package com.example.ccdemo;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.ccdemo.Model.UserLogin;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ManagerLoginVM extends ViewModel {

    private ManagerLoginUC ucManager;


    @Inject
    public ManagerLoginVM(ManagerLoginUC ucManager) {
        this.ucManager = ucManager;
    }
    public String validateLogin(UserLogin userLogin){
        Log.d("ManagerLoginVM", "validateLogin() called");
        return ucManager.validateLogin(userLogin);
    }
}