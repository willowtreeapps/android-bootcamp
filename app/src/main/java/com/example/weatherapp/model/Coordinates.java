package com.example.weatherapp.model;

import com.google.gson.annotations.SerializedName;

/**
 *
 */
class Coordinates {

    @SerializedName("lat")
    double latitude;

    @SerializedName("lon")
    double longitude;
}