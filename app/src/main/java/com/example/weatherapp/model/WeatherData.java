package com.example.weatherapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class WeatherData implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.timestamp);
        dest.writeParcelable(this.temperature, flags);
        dest.writeDouble(this.pressure);
        dest.writeDouble(this.humidity);
        dest.writeList(this.weather);
        dest.writeDouble(this.speed);
        dest.writeDouble(this.deg);
        dest.writeDouble(this.clouds);
        dest.writeDouble(this.rain);
    }

    public WeatherData() {
    }

    protected WeatherData(Parcel in) {
        this.timestamp = in.readDouble();
        this.temperature = in.readParcelable(Temperature.class.getClassLoader());
        this.pressure = in.readDouble();
        this.humidity = in.readDouble();
        this.weather = new ArrayList<Weather>();
        in.readList(this.weather, Weather.class.getClassLoader());
        this.speed = in.readDouble();
        this.deg = in.readDouble();
        this.clouds = in.readDouble();
        this.rain = in.readDouble();
    }

    public static final Parcelable.Creator<WeatherData> CREATOR = new Parcelable.Creator<WeatherData>() {
        @Override
        public WeatherData createFromParcel(Parcel source) {
            return new WeatherData(source);
        }

        @Override
        public WeatherData[] newArray(int size) {
            return new WeatherData[size];
        }
    };
}
