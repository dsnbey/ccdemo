package com.example.ccdemo.Model;

public class FieldOrganization {

    private String address, name;

    public FieldOrganization(String address, String name) {
        this.address = address;
        this.name = name;
    }

    public FieldOrganization() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
