package com.example.weatherapp;

import com.example.weatherapp.api.WeatherService;
import com.example.weatherapp.model.CurrentWeather;
import com.example.weatherapp.model.Forecast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherAppPresenter implements Presenter {

    WeatherService weatherService;

    WeatherAppView weatherAppView;

    WeatherAppPresenter(WeatherService weatherService){
        this.weatherService = weatherService;
    }

    @Override
    public void takeView(WeatherAppView view) {
        this.weatherAppView = view;
    }

    @Override
    public void update() {

        if(weatherAppView.getCity().isEmpty()){
            weatherAppView.displayError("Please enter a city");
            return;
        }

        weatherService.loadCurrent(weatherAppView.getCity(), new Callback<CurrentWeather>() {
            @Override
            public void onResponse(Call<CurrentWeather> call, Response<CurrentWeather> response) {
                if (response.body() == null) {
                    failFetchingWeather();
                } else {
                    weatherAppView.setCurrentWeather(response.body());
                }
            }

            @Override
            public void onFailure(Call<CurrentWeather> call, Throwable t) {
                failFetchingWeather();
            }
        });

        weatherService.loadForecast(weatherAppView.getCity(), new Callback<Forecast>() {
            @Override
            public void onResponse(Call<Forecast> call, Response<Forecast> response) {
                if (response.body() == null) {
                    failFetchingWeather();
                } else {
                    weatherAppView.setForecast(response.body());
                }
            }

            @Override
            public void onFailure(Call<Forecast> call, Throwable t) {
                failFetchingWeather();
            }
        });

    }

    private void failFetchingWeather() {
        weatherAppView.displayError("Something went wrong when loading weather forecast.");
    }

}
