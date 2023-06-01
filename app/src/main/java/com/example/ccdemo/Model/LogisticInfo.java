package com.example.ccdemo.Model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 *  Same class denoted as Logistics in doc
 */
@Entity
public class LogisticInfo {

    @PrimaryKey
    private int id = 1;
    private int truckSize;
    private String getName, getAddress, dropName, dropAddress, status, pictureUrl;
    private boolean getStatus, dropStatus;
    @Ignore
    private InventoryList inventoryList;

    public LogisticInfo(String getName, String getAddress, String dropName, String dropAddress, String status, String pictureUrl, boolean getStatus, boolean dropStatus, InventoryList inventoryList, int truckSize) {
        this.getName = getName;
        this.getAddress = getAddress;
        this.dropName = dropName;
        this.dropAddress = dropAddress;
        this.status = status;
        this.pictureUrl = pictureUrl;
        this.getStatus = getStatus;
        this.dropStatus = dropStatus;
        this.inventoryList = inventoryList;
        this.truckSize = truckSize;
    }

    public int getTruckSize() {
        return truckSize;
    }

    public void setTruckSize(int truckSize) {
        this.truckSize = truckSize;
    }

    public LogisticInfo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGetName() {
        return getName;
    }

    public void setGetName(String getName) {
        this.getName = getName;
    }

    public String getGetAddress() {
        return getAddress;
    }

    public void setGetAddress(String getAddress) {
        this.getAddress = getAddress;
    }

    public String getDropName() {
        return dropName;
    }

    public void setDropName(String dropName) {
        this.dropName = dropName;
    }

    public String getDropAddress() {
        return dropAddress;
    }

    public void setDropAddress(String dropAddress) {
        this.dropAddress = dropAddress;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public boolean isGetStatus() {
        return getStatus;
    }

    public void setGetStatus(boolean getStatus) {
        this.getStatus = getStatus;
    }

    public boolean isDropStatus() {
        return dropStatus;
    }

    public void setDropStatus(boolean dropStatus) {
        this.dropStatus = dropStatus;
    }

    public InventoryList getInventoryList() {
        return inventoryList;
    }

    public void setInventoryList(InventoryList inventoryList) {
        this.inventoryList = inventoryList;
    }
}