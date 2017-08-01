package com.example.weatherapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Forcast
 */
public class Forecast implements Parcelable {

    public City city;

    @SerializedName("cod")
    public String statusCode;

    public double message;

    @SerializedName("cnt")
    public double count;

    @SerializedName("list")
    public List<WeatherData> weather;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.city, flags);
        dest.writeString(this.statusCode);
        dest.writeDouble(this.message);
        dest.writeDouble(this.count);
        dest.writeTypedList(this.weather);
    }

    public Forecast() {
    }

    protected Forecast(Parcel in) {
        this.city = in.readParcelable(City.class.getClassLoader());
        this.statusCode = in.readString();
        this.message = in.readDouble();
        this.count = in.readDouble();
        this.weather = in.createTypedArrayList(WeatherData.CREATOR);
    }

    public static final Parcelable.Creator<Forecast> CREATOR = new Parcelable.Creator<Forecast>() {
        @Override
        public Forecast createFromParcel(Parcel source) {
            return new Forecast(source);
        }

        @Override
        public Forecast[] newArray(int size) {
            return new Forecast[size];
        }
    };
}
