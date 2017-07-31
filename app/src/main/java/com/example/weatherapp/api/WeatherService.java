package com.example.weatherapp.api;

import android.support.annotation.NonNull;

import com.example.weatherapp.model.CurrentWeather;
import com.example.weatherapp.model.Forecast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class WeatherService {

    @NonNull
    private OpenWeatherMapApi api;

    public WeatherService(@NonNull OpenWeatherMapApi api) {
        this.api = api;
    }


    public void loadForecast(final @NonNull String location, final @NonNull Callback<Forecast> forecastCallback) {
        Call<Forecast> test = api.loadForecast(location);
        test.enqueue(forecastCallback);
    }

    public void loadCurrent(@NonNull String location, @NonNull Callback<CurrentWeather> currentWeatherCallback) {
        Call<CurrentWeather> test = api.loadCurrent(location);
        test.enqueue(currentWeatherCallback);
    }

    public interface OpenWeatherMapApi {

        String BASE_URL = "http://api.openweathermap.org/";

        String API_KEY = "f8fdae74c29544baebdb927d392c5538";

        String units = "metric";

        /**
         * Ref: http://openweathermap.org/forecast16
         *
         * @param location City name and/or country code
         */
        @GET("/data/2.5/forecast/daily?mode=json&units=" + units + "&cnt=14&appid=" + API_KEY)
        Call<Forecast> loadForecast(@Query("q") String location);

        /**
         * Ref: http://openweathermap.org/current
         *
         * @param location City name and/or country code
         */
        @GET("/data/2.5/weather?mode=json&units=" + units + "&appid=" + API_KEY)
        Call<CurrentWeather> loadCurrent(@Query("q") String location);
    }
}
