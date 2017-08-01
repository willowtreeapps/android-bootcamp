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
 * Currently just displays forecast data
 */
public class WeatherDataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /**
     * Gives nice formatting. Example, Tuesday 12.
     */
    private static final SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE dd", Locale.US);

    private List<WeatherData> forecastData;

    private Context context;

    private static final int TYPE_FORECAST = 1;


    public WeatherDataAdapter(Context context) {
        this.context = context;
        this.forecastData = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_FORECAST:
                return new ForecastViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.weather_item, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ForecastViewHolder) {
            ForecastViewHolder forecastViewHolder = (ForecastViewHolder) holder;
            WeatherData data = forecastData.get(position);

            Drawable drawable = ContextCompat.getDrawable(context, UiUtils.getWeatherIconResId(data.weather.get(0).main));
            forecastViewHolder.icon.setImageDrawable(drawable);
            forecastViewHolder.date.setText(dayFormat.format(new Date((long) data.timestamp * 1000)));
            forecastViewHolder.weather.setText(data.weather.get(0).description);
            forecastViewHolder.high.setText(context.getResources().getString(R.string.temperature, (int) data.temperature.max));
            forecastViewHolder.low.setText(context.getResources().getString(R.string.temperature, (int) data.temperature.min));
        }
    }

    @Override
    public int getItemViewType(int position) {
        return TYPE_FORECAST;
    }

    @Override
    public int getItemCount() {
        return forecastData.size();
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
}
