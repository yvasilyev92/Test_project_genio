package com.development.test_project_genio;

import io.realm.RealmObject;

/**
 * Created by Yevgeniy on 9/11/2017.
 */

public class Earthquake extends RealmObject{

    private double magnitude;
    private String location;

    public Earthquake(){
        super();
    }


    public Earthquake(double magnitude, String location){
        this.magnitude = magnitude;
        this.location = location;
    }


    public double getMagnitude(){
        return magnitude;
    }

    public String getLocation(){
        return location;
    }

    public void setMagnitude(double magnitude){
        this.magnitude = magnitude;
    }

    public void setLocation(String location){
        this.location = location;
    }


}
