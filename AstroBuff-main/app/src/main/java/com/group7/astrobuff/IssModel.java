package com.group7.astrobuff;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class IssModel {

    private String latitude;
    private String longitude;
    private Integer timestamp;
    private String message;

    public IssModel(String latitude, String longitude, Integer timestamp, String message){
        this.latitude = latitude;
        this.longitude = longitude;
        this.timestamp = timestamp;
        this.message = message;
    }
    public  IssModel(){}

    //Getters and setters
    public String getLatitude() {  return latitude; }

    public void setLatitude(String latitude) { this.latitude = latitude;}

    public String getLongitude() { return longitude; }

    public void setLongitude(String longitude) { this.longitude = longitude;}

    public Integer getTimestamp() { return timestamp; }

    public void setTimestamp(Integer timestamp) {this.timestamp = timestamp;}

    public String getMessage() { return message; }

    public void setMessage(String message) { this.message = message; }
}