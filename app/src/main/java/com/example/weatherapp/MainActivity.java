package com.example.weatherapp;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weatherapp.api.NetworkModule;
import com.example.weatherapp.api.WeatherService;
import com.example.weatherapp.model.CurrentWeather;
import com.example.weatherapp.model.Forecast;


public class MainActivity extends AppCompatActivity implements WeatherAppView {

    private static final String STATE_INPUT = "state_input";
    private static final String STATE_CURRENT_WEATHER = "state_current_weather";
    private static final String STATE_FORECAST= "state_forecast";

    private ImageView icon;
    private TextView name;
    private TextView weather;
    private TextView high;
    private TextView low;
    private TextView current;
    private EditText editText;
    private Button viewForecastButton;

    private WeatherAppPresenter weatherAppPresenter;

    WeatherService weatherService;

    //This will be passed to ForecastActivity on button click
    private Forecast forecast;

    //Data to be displayed in this Activity
    //Storing it here so we can restore state easily
    private CurrentWeather currentWeather;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        NetworkModule networkModule = new NetworkModule(WeatherService.OpenWeatherMapApi.BASE_URL);
        weatherService = networkModule.getWeatherService();

        weatherAppPresenter = new WeatherAppPresenter(weatherService);
        weatherAppPresenter.takeView(this);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button getWeatherButton = (Button) findViewById(R.id.get_weather_button);
        getWeatherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weatherAppPresenter.update();
            }
        });

        viewForecastButton = (Button) findViewById(R.id.view_forecast_button);

        //Will be enabled once we get data back
        viewForecastButton.setEnabled(false);
        viewForecastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (forecast != null) {
                    startActivity(ForecastActivity.createStartIntent(MainActivity.this, forecast.city.name, forecast));
                }
            }
        });

        editText = (EditText) findViewById(R.id.enter_city);

        icon = (ImageView) findViewById(R.id.header_weather_icon);
        name = (TextView) findViewById(R.id.city_name);
        current = (TextView) findViewById(R.id.current_temp);
        weather = (TextView) findViewById(R.id.header_weather_desc);
        high = (TextView) findViewById(R.id.header_temp_high);
        low = (TextView) findViewById(R.id.header_temp_low);

        if (savedInstanceState != null) {
            forecast = savedInstanceState.getParcelable(STATE_FORECAST);
            currentWeather = savedInstanceState.getParcelable(STATE_CURRENT_WEATHER);
            editText.setText(savedInstanceState.getString(STATE_INPUT));

            if (currentWeather != null && forecast != null) {
                setForecast(forecast);
                setCurrentWeather(currentWeather);
            }
        }
    }

    @Override
    public String getCity() {
        return editText.getText().toString();
    }

    @Override
    public void setCurrentWeather(CurrentWeather currentWeather) {
        this.currentWeather = currentWeather;
        Drawable drawable = ContextCompat.getDrawable(this, UiUtils.getWeatherIconResId(currentWeather.getWeather().get(0).main));
        icon.setImageDrawable(drawable);
        name.setText(currentWeather.getName());
        weather.setText(currentWeather.getWeather().get(0).main);
        high.setText(getResources().getString(R.string.temperature, (int) currentWeather.getMain().temp_max));
        low.setText(getResources().getString(R.string.temperature, (int) currentWeather.getMain().temp_min));
        current.setText(getResources().getString(R.string.temperature, (int) currentWeather.getMain().temp));
    }

    @Override
    public void setForecast(Forecast forecast) {
        this.forecast = forecast;
        viewForecastButton.setEnabled(true);
    }

    @Override
    public void displayError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(STATE_CURRENT_WEATHER, currentWeather);
        outState.putParcelable(STATE_FORECAST, forecast);
        outState.putString(STATE_INPUT, editText.getText().toString());
    }
}
