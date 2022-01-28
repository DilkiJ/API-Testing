package com.hit.apitestingapplication;

import com.google.gson.annotations.SerializedName;

public class DataModel {

    //template for the data that we are going to parse
    private int userId;
    private int id;
    private String title;
    private boolean completed;


    //    @SerializedName("userId")
    //    private int uId;


    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return completed;
    }
}
