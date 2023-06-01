package com.example.ccdemo;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.ccdemo.Model.InventoryList;
import com.example.ccdemo.Model.Payload;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CloudRestApi {

    CloudApiService apiService;

    public CloudRestApi() {
        this.apiService = RetrofitClientForCloud.createService();
    }

    @SuppressLint("CheckResult")
    public boolean decideDropPlace(InventoryList inventoryList, String driverName) {

        final boolean[] status = {true};

        Payload payload = new Payload();
        payload.setInventoryList(inventoryList);
        payload.setDriverName(driverName);

        apiService.sendRequest(payload)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Object>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.d("CloudRestApi", " decideDropPlace onSubscribe: ");
                    }

                    @Override
                    public void onSuccess(@NonNull Object o) {
                        Log.d("CloudRestApi", " decideDropPlace onSuccess: ");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("CloudRestApi", " decideDropPlace onError " + e.getMessage());
                        e.printStackTrace();
                        status[0] = false;
                    }
                });
        return status[0];

    }
}