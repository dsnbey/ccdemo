package com.example.ccdemo.Model;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class UserLogin {

    @NonNull
    @PrimaryKey
    private String userName;
    private String password;

    public UserLogin(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public UserLogin() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
