package com.example.ccdemo.Model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {

    @NonNull
    @PrimaryKey
    private String userName;
    private String userType; //"USER_TYPES: VIB, OV, TL, TO, HQO, AHQO"

    public User(String userName, String userType) {
        this.userName = userName;
        this.userType = userType;
    }

    public User() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}