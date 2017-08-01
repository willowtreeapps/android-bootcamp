package com.example.weatherapp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 *
 */
public class Weather implements Parcelable {

    public double id;

    public String main;

    public String description;

    public String icon;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.id);
        dest.writeString(this.main);
        dest.writeString(this.description);
        dest.writeString(this.icon);
    }

    public Weather() {
    }

    protected Weather(Parcel in) {
        this.id = in.readDouble();
        this.main = in.readString();
        this.description = in.readString();
        this.icon = in.readString();
    }

    public static final Parcelable.Creator<Weather> CREATOR = new Parcelable.Creator<Weather>() {
        @Override
        public Weather createFromParcel(Parcel source) {
            return new Weather(source);
        }

        @Override
        public Weather[] newArray(int size) {
            return new Weather[size];
        }
    };
}