package com.example.weatherapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherData {

    @SerializedName("dt")
    public double timestamp;

    @SerializedName("temp")
    public Temperature temperature;

    public double pressure;

    public double humidity;

    public List<Weather> weather;

    public double speed;

    public double deg;

    public double clouds;

    public double rain;

}
