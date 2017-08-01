package com.example.weatherapp.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Reference: http://openweathermap.org/forecast16
 */
public class City implements Parcelable {

    public String id;

    public String name;

    @SerializedName("coord")
    public Coordinates coordinates;

    public String country;

    public double population;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeParcelable(this.coordinates, flags);
        dest.writeString(this.country);
        dest.writeDouble(this.population);
    }

    public City() {
    }

    protected City(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.coordinates = in.readParcelable(Coordinates.class.getClassLoader());
        this.country = in.readString();
        this.population = in.readDouble();
    }

    public static final Parcelable.Creator<City> CREATOR = new Parcelable.Creator<City>() {
        @Override
        public City createFromParcel(Parcel source) {
            return new City(source);
        }

        @Override
        public City[] newArray(int size) {
            return new City[size];
        }
    };
}