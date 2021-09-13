package com.example.trailapp;

public class Current {

           float temp_c;
            float temp_f;

    public Current(float temp_c, float temp_f) {
        this.temp_c = temp_c;
        this.temp_f = temp_f;
    }

    public float getTemp_c() {
        return temp_c;
    }

    public void setTemp_c(float temp_c) {
        this.temp_c = temp_c;
    }

    public float getTemp_f() {
        return temp_f;
    }

    public void setTemp_f(float temp_f) {
        this.temp_f = temp_f;
    }
}
