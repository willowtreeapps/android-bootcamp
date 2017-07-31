package com.example.weatherapp;

import com.example.weatherapp.model.CurrentWeather;
import com.example.weatherapp.model.Forecast;

public interface WeatherAppView {

    String getCity();

    void setCurrentWeather(CurrentWeather currentWeather);

    void setForecast(Forecast forecast);

    void displayError(String message);
}
