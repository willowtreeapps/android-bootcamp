package com.example.weatherapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Current weather information. Left a lot of things out of the api
 * to keep things brief
 */
public class CurrentWeather implements Parcelable {

    private Coordinates coord;

    private List<Weather> weather;

    private String name;

    private Main main;

    private int cod;


    public Coordinates getCoord() {
        return coord;
    }

    public void setCoord(Coordinates coord) {
        this.coord = coord;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.coord, flags);
        dest.writeTypedList(this.weather);
        dest.writeString(this.name);
        dest.writeParcelable(this.main, flags);
        dest.writeInt(this.cod);
    }

    public CurrentWeather() {
    }

    protected CurrentWeather(Parcel in) {
        this.coord = in.readParcelable(Coordinates.class.getClassLoader());
        this.weather = in.createTypedArrayList(Weather.CREATOR);
        this.name = in.readString();
        this.main = in.readParcelable(Main.class.getClassLoader());
        this.cod = in.readInt();
    }

    public static final Parcelable.Creator<CurrentWeather> CREATOR = new Parcelable.Creator<CurrentWeather>() {
        @Override
        public CurrentWeather createFromParcel(Parcel source) {
            return new CurrentWeather(source);
        }

        @Override
        public CurrentWeather[] newArray(int size) {
            return new CurrentWeather[size];
        }
    };
}
