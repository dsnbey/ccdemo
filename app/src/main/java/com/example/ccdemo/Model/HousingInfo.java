package com.example.ccdemo.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class HousingInfo {

    @PrimaryKey
    private int id = 1;

    private int hasHouse, badCondition, noHouse;

    public HousingInfo(int id, int hasHouse, int badCondition, int noHouse) {
        this.id = id;
        this.hasHouse = hasHouse;
        this.badCondition = badCondition;
        this.noHouse = noHouse;
    }

    public HousingInfo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHasHouse() {
        return hasHouse;
    }

    public void setHasHouse(int hasHouse) {
        this.hasHouse = hasHouse;
    }

    public int getBadCondition() {
        return badCondition;
    }

    public void setBadCondition(int badCondition) {
        this.badCondition = badCondition;
    }

    public int getNoHouse() {
        return noHouse;
    }

    public void setNoHouse(int noHouse) {
        this.noHouse = noHouse;
    }
}
