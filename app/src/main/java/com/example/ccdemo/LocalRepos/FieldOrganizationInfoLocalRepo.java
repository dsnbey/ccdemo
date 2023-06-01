package com.example.ccdemo.LocalRepos;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.ccdemo.LocalDBDaos.FieldOrganizationDao;
import com.example.ccdemo.LocalDBDaos.LocalDB;
import com.example.ccdemo.Model.DemographicInfo;
import com.example.ccdemo.Model.HousingInfo;
import com.example.ccdemo.Model.InventoryList;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.observers.DisposableCompletableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FieldOrganizationInfoLocalRepo {

    private final LocalDB db;
    private FieldOrganizationDao fieldOrgDao;

    @Inject
    public FieldOrganizationInfoLocalRepo(LocalDB db) {
        this.db = db;
        this.fieldOrgDao = db.fieldOrganizationDao();
    }

    @SuppressLint("CheckResult")
    public void recordDemographicInfo(DemographicInfo info) {

        fieldOrgDao.recordDemographicInfo(info)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        Log.d("FieldOrganizationInfoLocalRepo", "recordDemographicInfo onComplete: ");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("FieldOrganizationInfoLocalRepo", "recordDemographicInfo onError: " + e.getMessage());
                        e.printStackTrace();
                    }
                });
    }

    @SuppressLint("CheckResult")
    public void recordHousingInfo(HousingInfo info) {

        fieldOrgDao.recordHousingInfo(info)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        Log.d("FieldOrganizationInfoLocalRepo", "recordHousingInfo onComplete: ");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("FieldOrganizationInfoLocalRepo", "recordHousingInfo onError: " + e.getMessage());
                        e.printStackTrace();
                    }
                });
    }

    @SuppressLint("CheckResult")
    public void recordInventoryInfo(InventoryList info) {

        fieldOrgDao.recordInventoryInfo(info)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        Log.d("FieldOrganizationInfoLocalRepo", "recordInventoryInfo onComplete: ");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("FieldOrganizationInfoLocalRepo", "recordInventoryInfo onError: " + e.getMessage());
                        e.printStackTrace();
                    }
                });
    }

    @SuppressLint("CheckResult")
    public void recordArrivingAid(InventoryList info) {

        fieldOrgDao.recordArrivingAid(info)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        Log.d("FieldOrganizationInfoLocalRepo", "recordArrivingAid onComplete: ");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("FieldOrganizationInfoLocalRepo", "recordArrivingAid  onError: " + e.getMessage());
                        e.printStackTrace();
                    }
                });
    }

    public Flowable<DemographicInfo> getDemographicInfo() {
        return fieldOrgDao.getDemographicInfo();
    }

    public Flowable<HousingInfo> getHousingInfo() {
        return fieldOrgDao.getHousingInfo();
    }

    public Flowable<InventoryList> getInventoryInfo() {
        return fieldOrgDao.getInventoryInfo();
    }

    public Flowable<InventoryList> getArrivingInfo() {
        return fieldOrgDao.getArrivingInfo();
    }
}
