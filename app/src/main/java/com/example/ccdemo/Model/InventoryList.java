package com.example.ccdemo.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class InventoryList {


    @PrimaryKey
    private int id; // 1 for current inventory, 2 for arriving aid
    private int food, heater, manCloth, womanCloth, childCloth, hygene, kitchenMaterial, powerbank;



    public InventoryList(int id, int food, int heater, int manCloth, int womanCloth, int childCloth, int hygene, int kitchenMaterial, int powerbank) {
        this.food = food;
        this.heater = heater;
        this.manCloth = manCloth;
        this.womanCloth = womanCloth;
        this.childCloth = childCloth;
        this.hygene = hygene;
        this.kitchenMaterial = kitchenMaterial;
        this.powerbank = powerbank;
        this.id = id;
    }

    public InventoryList() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public int getHeater() {
        return heater;
    }

    public void setHeater(int heater) {
        this.heater = heater;
    }

    public int getManCloth() {
        return manCloth;
    }

    public void setManCloth(int manCloth) {
        this.manCloth = manCloth;
    }

    public int getWomanCloth() {
        return womanCloth;
    }

    public void setWomanCloth(int womanCloth) {
        this.womanCloth = womanCloth;
    }

    public int getChildCloth() {
        return childCloth;
    }

    public void setChildCloth(int childCloth) {
        this.childCloth = childCloth;
    }

    public int getHygene() {
        return hygene;
    }

    public void setHygene(int hygene) {
        this.hygene = hygene;
    }

    public int getKitchenMaterial() {
        return kitchenMaterial;
    }

    public void setKitchenMaterial(int kitchenMaterial) {
        this.kitchenMaterial = kitchenMaterial;
    }

    public int getPowerbank() {
        return powerbank;
    }

    public void setPowerbank(int powerbank) {
        this.powerbank = powerbank;
    }
}
