package com.example.weatherapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weatherapp.model.CurrentWeather;
import com.example.weatherapp.model.Forecast;
import com.example.weatherapp.model.WeatherData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Adapts to two view types. Header is placed at the top of the list
 * followed by the forecasts.
 */
public class WeatherDataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /**
     * Gives nice formatting. Example, Tuesday 12.
     */
    private static final SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE dd", Locale.US);

    private CurrentWeather currentWeather;

    private List<WeatherData> forecastData;

    private Context context;

    private static final int TYPE_HEADER = 0;

    private static final int TYPE_FORECAST = 1;


    public WeatherDataAdapter(Context context) {
        this.context = context;
        this.forecastData = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_HEADER:
                return new ForecastViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.weather_item, parent, false));
            case TYPE_FORECAST:
                return new HeaderViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.weather_current, parent, false));
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ForecastViewHolder) {
            if(forecastData.size() > 0) {
                ForecastViewHolder forecastViewHolder = (ForecastViewHolder) holder;
                WeatherData data = forecastData.get(position - 1);

                Drawable drawable = ContextCompat.getDrawable(context, getWeatherIcon(data.weather.get(0).main));
                forecastViewHolder.icon.setImageDrawable(drawable);
                forecastViewHolder.date.setText(dayFormat.format(new Date((long) data.timestamp * 1000)));
                forecastViewHolder.weather.setText(data.weather.get(0).description);
                forecastViewHolder.high.setText(context.getResources().getString(R.string.temperature, (int) data.temperature.max));
                forecastViewHolder.low.setText(context.getResources().getString(R.string.temperature, (int) data.temperature.min));
            }
        } else if (holder instanceof HeaderViewHolder){
            HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
            if(currentWeather != null){
                Drawable drawable = ContextCompat.getDrawable(context, getWeatherIcon(currentWeather.getWeather().get(0).main));
                headerViewHolder.icon.setImageDrawable(drawable);
                headerViewHolder.name.setText(currentWeather.getName());
                headerViewHolder.weather.setText(currentWeather.getWeather().get(0).main);
                headerViewHolder.high.setText(context.getResources().getString(R.string.temperature, (int) currentWeather.getMain().temp_max));
                headerViewHolder.low.setText(context.getResources().getString(R.string.temperature, (int) currentWeather.getMain().temp_min));
                headerViewHolder.current.setText(context.getResources().getString(R.string.temperature, (int) currentWeather.getMain().temp));
            }
        }

    }

    /**
     * Ref: http://openweathermap.org/weather-conditions
     *
     * @param condition weather condition category.
     * @return icon resource id
     */
    public int getWeatherIcon(String condition) {
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

    @Override
    public int getItemViewType(int position) {
        return position != 0 ? TYPE_HEADER : TYPE_FORECAST;
    }

    @Override
    public int getItemCount() {
        return forecastData.size() + 1;
    }

    public void setCurrentWeather(CurrentWeather currentWeather) {
        this.currentWeather = currentWeather;
        notifyDataSetChanged();
    }

    public void setForecast(Forecast forecast) {
        forecastData.clear();
        forecastData.addAll(forecast.weather);
        notifyDataSetChanged();
    }

    public static class ForecastViewHolder extends RecyclerView.ViewHolder {

        ImageView icon;

        TextView date;

        TextView weather;

        TextView high;

        TextView low;

        public ForecastViewHolder(View itemView) {
            super(itemView);
            icon = (ImageView) itemView.findViewById(R.id.weather_icon);
            date = (TextView) itemView.findViewById(R.id.date);
            weather = (TextView) itemView.findViewById(R.id.weather_desc);
            high = (TextView) itemView.findViewById(R.id.temp_high);
            low = (TextView) itemView.findViewById(R.id.temp_low);
        }
    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {

        ImageView icon;

        TextView name;

        TextView weather;

        TextView high;

        TextView low;

        TextView current;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            icon = (ImageView) itemView.findViewById(R.id.header_weather_icon);
            name = (TextView) itemView.findViewById(R.id.city_name);
            current = (TextView) itemView.findViewById(R.id.current_temp);
            weather = (TextView) itemView.findViewById(R.id.header_weather_desc);
            high = (TextView) itemView.findViewById(R.id.header_temp_high);
            low = (TextView) itemView.findViewById(R.id.header_temp_low);
        }
    }
}
