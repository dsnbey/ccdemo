package com.example.ccdemo.Model;

public class NeedItem {
    private String typeOfNeed;
    private String urgency;

    public NeedItem(String typeOfNeed, String urgency) {
        this.typeOfNeed = typeOfNeed;
        this.urgency = urgency;
    }

    public String getTypeOfNeed() {
        return typeOfNeed;
    }

    public String getUrgency() {
        return urgency;
    }
}
