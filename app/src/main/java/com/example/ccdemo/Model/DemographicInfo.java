package com.example.ccdemo.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class DemographicInfo {

    @PrimaryKey
    private int id = 1;

    private int M0_3, M3_15, M15_64, M65, W0_3, W3_15, W15_64, W65;

    public DemographicInfo(int m0_3, int m3_15, int m15_64, int m65, int w0_3, int w3_15, int w15_64, int w65) {
        M0_3 = m0_3;
        M3_15 = m3_15;
        M15_64 = m15_64;
        M65 = m65;
        W0_3 = w0_3;
        W3_15 = w3_15;
        W15_64 = w15_64;
        W65 = w65;
    }

    public DemographicInfo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getM0_3() {
        return M0_3;
    }

    public void setM0_3(int m0_3) {
        M0_3 = m0_3;
    }

    public int getM3_15() {
        return M3_15;
    }

    public void setM3_15(int m3_15) {
        M3_15 = m3_15;
    }

    public int getM15_64() {
        return M15_64;
    }

    public void setM15_64(int m15_64) {
        M15_64 = m15_64;
    }

    public int getM65() {
        return M65;
    }

    public void setM65(int m65) {
        M65 = m65;
    }

    public int getW0_3() {
        return W0_3;
    }

    public void setW0_3(int w0_3) {
        W0_3 = w0_3;
    }

    public int getW3_15() {
        return W3_15;
    }

    public void setW3_15(int w3_15) {
        W3_15 = w3_15;
    }

    public int getW15_64() {
        return W15_64;
    }

    public void setW15_64(int w15_64) {
        W15_64 = w15_64;
    }

    public int getW65() {
        return W65;
    }

    public void setW65(int w65) {
        W65 = w65;
    }
}
