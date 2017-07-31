package com.example.weatherapp;

public interface Presenter {

    void takeView(WeatherAppView view);

    void update();
}
