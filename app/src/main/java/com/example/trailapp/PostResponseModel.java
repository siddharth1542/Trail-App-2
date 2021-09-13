package com.example.trailapp;

public class PostResponseModel {

    String State;
    String District;

    public PostResponseModel(String state, String district) {
        this.State = state;
        District = district;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        this.State = state;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String district) {
        District = district;
    }



}
