package com.example.trailapp;


import java.util.List;

public class CheckResponseModel {

    String Message;
    String Status;
    List<PostResponseModel> PostOffice;


    public CheckResponseModel(String message, String status, List<PostResponseModel> postOffice) {
        Message = message;
        Status = status;
        PostOffice = postOffice;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public List<PostResponseModel> getPostOffice() {
        return PostOffice;
    }

    public void setPostOffice(List<PostResponseModel> postOffice) {
        PostOffice = postOffice;
    }
}