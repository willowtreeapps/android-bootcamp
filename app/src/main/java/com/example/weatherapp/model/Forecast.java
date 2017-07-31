package com.example.weatherapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Forcast
 */
public class Forecast {

    public City city;

    @SerializedName("cod")
    public String statusCode;

    public double message;

    @SerializedName("cnt")
    public double count;

    @SerializedName("list")
    public List<WeatherData> weather;

}
