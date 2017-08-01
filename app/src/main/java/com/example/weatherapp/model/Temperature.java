package com.example.weatherapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Temperature implements Parcelable {

    public double day;

    public double min;

    public double max;

    public double night;

    public double eve;

    public double morn;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.day);
        dest.writeDouble(this.min);
        dest.writeDouble(this.max);
        dest.writeDouble(this.night);
        dest.writeDouble(this.eve);
        dest.writeDouble(this.morn);
    }

    public Temperature() {
    }

    protected Temperature(Parcel in) {
        this.day = in.readDouble();
        this.min = in.readDouble();
        this.max = in.readDouble();
        this.night = in.readDouble();
        this.eve = in.readDouble();
        this.morn = in.readDouble();
    }

    public static final Parcelable.Creator<Temperature> CREATOR = new Parcelable.Creator<Temperature>() {
        @Override
        public Temperature createFromParcel(Parcel source) {
            return new Temperature(source);
        }

        @Override
        public Temperature[] newArray(int size) {
            return new Temperature[size];
        }
    };
}
