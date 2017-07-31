package com.example.weatherapp.model;

import java.util.List;

/**
 * Current weather information. Left a lot of things out of the api
 * to keep things brief
 */
public class CurrentWeather {

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

    public class Main {

        public double temp;

        public double temp_min;

        public double temp_max;
    }
}
