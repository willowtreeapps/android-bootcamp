package com.example.weatherapp;

public class UiUtils {

    /**
     * Ref: http://openweathermap.org/weather-conditions
     *
     * @param condition weather condition category.
     * @return icon resource id
     */
    public static int getWeatherIconResId(String condition) {
        switch (condition.toLowerCase()) {
            case "clear":
                return R.drawable.ic_weather_sunny_24dp;
            case "thunderstorm":
                return R.drawable.ic_weather_thunderstorms_24dp;
            case "drizzle":
                return R.drawable.ic_weather_drizzle_24dp;
            case "rain":
                return R.drawable.ic_weather_heavy_rain_24dp;
            case "clouds":
                return R.drawable.ic_weather_partly_cloudy_24dp;
            default:
                return R.drawable.ic_weather_alert_24dp;
        }
    }
}
