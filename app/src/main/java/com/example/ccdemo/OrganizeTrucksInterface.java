package com.example.ccdemo;

import com.example.ccdemo.Model.DriverItem;

import java.util.List;

public interface OrganizeTrucksInterface {
    void setDisplay (List<DriverItem> list);
    void warnUser();
}