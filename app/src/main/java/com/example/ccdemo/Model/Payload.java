package com.example.ccdemo.Model;

public class Payload {

    private InventoryList inventoryList;
    private String driverName;

    public Payload(InventoryList inventoryList, String driverName) {
        this.inventoryList = inventoryList;
        this.driverName = driverName;
    }

    public Payload() {
    }

    public InventoryList getInventoryList() {
        return inventoryList;
    }

    public void setInventoryList(InventoryList inventoryList) {
        this.inventoryList = inventoryList;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }
}