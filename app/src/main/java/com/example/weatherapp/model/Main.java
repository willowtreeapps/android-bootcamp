package com.example.weatherapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Main implements Parcelable {

    public double temp;

    public double temp_min;

    public double temp_max;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.temp);
        dest.writeDouble(this.temp_min);
        dest.writeDouble(this.temp_max);
    }

    public Main() {
    }

    protected Main(Parcel in) {
        this.temp = in.readDouble();
        this.temp_min = in.readDouble();
        this.temp_max = in.readDouble();
    }

    public static final Parcelable.Creator<Main> CREATOR = new Parcelable.Creator<Main>() {
        @Override
        public Main createFromParcel(Parcel source) {
            return new Main(source);
        }

        @Override
        public Main[] newArray(int size) {
            return new Main[size];
        }
    };
}
