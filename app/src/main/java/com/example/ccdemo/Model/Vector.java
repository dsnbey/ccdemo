package com.example.ccdemo.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Vector {
    @SerializedName("id")
    private String id;

    @SerializedName("values")
    private List<Float> values;

    public Vector(String id, List<Float> values) {
        this.id = id;
        this.values = values;
    }

    public Vector() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Float> getValues() {
        return values;
    }

    public void setValues(List<Float> values) {
        this.values = values;
    }
}