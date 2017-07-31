package com.example.weatherapp.model;


import com.google.gson.annotations.SerializedName;

/**
 * Reference: http://openweathermap.org/forecast16
 */
class City {

    public String id;

    public String name;

    @SerializedName("coord")
    public Coordinates coordinates;

    public String country;

    public double population;

}