package com.example.weatherapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.example.weatherapp.model.Forecast;

public class ForecastActivity extends AppCompatActivity {

    private static final String EXTRA_FORECAST = "extra_forecast";
    private static final String EXTRA_CITY = "extra_city";

    public static Intent createStartIntent(Context context, String cityName, Forecast forecast) {
        return new Intent(context, ForecastActivity.class)
                .putExtra(EXTRA_FORECAST, forecast)
                .putExtra(EXTRA_CITY, cityName);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);

        if (!getIntent().hasExtra(EXTRA_FORECAST) || !getIntent().hasExtra(EXTRA_CITY)) {
            throw new IllegalArgumentException(getClass().getName() + " must be passed Forecast data");
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(getIntent().getStringExtra(EXTRA_CITY));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        WeatherDataAdapter weatherDataAdapter = new WeatherDataAdapter(this);

        RecyclerView listView = (RecyclerView) findViewById(R.id.recyclerview);
        listView.setLayoutManager(new LinearLayoutManager(this));
        listView.setAdapter(weatherDataAdapter);

        weatherDataAdapter.setForecast((Forecast) getIntent().getParcelableExtra(EXTRA_FORECAST));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
