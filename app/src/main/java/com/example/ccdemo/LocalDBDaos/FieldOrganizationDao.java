package com.example.ccdemo.LocalDBDaos.;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.catastrophecompass.DataLayer.Model.DemographicInfo;
import com.example.catastrophecompass.DataLayer.Model.HousingInfo;
import com.example.catastrophecompass.DataLayer.Model.InventoryList;
import com.example.ccdemo.Model.DemographicInfo;
import com.example.ccdemo.Model.HousingInfo;
import com.example.ccdemo.Model.InventoryList;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface FieldOrganizationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable recordDemographicInfo(DemographicInfo info);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable recordHousingInfo(HousingInfo info);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable recordInventoryInfo(InventoryList list);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable recordArrivingAid(InventoryList list);

    @Query("SELECT * FROM DemographicInfo")
    Flowable<DemographicInfo> getDemographicInfo();

    @Query("SELECT * FROM HousingInfo")
    Flowable<HousingInfo> getHousingInfo();

    @Query("SELECT * FROM InventoryList WHERE id = 1")
    Flowable<InventoryList> getInventoryInfo();

    @Query("SELECT * FROM InventoryList WHERE id = 2")
    Flowable<InventoryList> getArrivingInfo();


}
