package com.development.test_project_genio;

/**
 * Created by Yevgeniy on 9/11/2017.
 */

public class Earthquake {

    private double magnitude;
    private String location;

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

}
