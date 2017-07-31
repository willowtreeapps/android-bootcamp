package com.example.weatherapp.api;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkModule {

    @NonNull
    private String baseUrl;

    @NonNull
    private OkHttpClient okHttpClient;

    @NonNull
    private Retrofit retrofit;

    @NonNull
    private WeatherService weatherService;

    public NetworkModule(@NonNull String baseUrl) {
        this.baseUrl = baseUrl;

        okHttpClient = createOkHttpClient();
        retrofit = createRetrofit(okHttpClient);
        weatherService = createWeatherService(retrofit);
    }


    private OkHttpClient createOkHttpClient() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request request = original.newBuilder()
                        .header("Accept", "application/json")
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        });
        return httpClient.build();
    }

    private Retrofit createRetrofit(OkHttpClient client) {
        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .addConverterFactory(GsonConverterFactory.create()).client(client);
        return builder.build();

    }

    private WeatherService createWeatherService(Retrofit retrofit) {
        WeatherService.OpenWeatherMapApi api = retrofit.create(WeatherService.OpenWeatherMapApi.class);
        return new WeatherService(api);
    }

    @NonNull
    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    @NonNull
    public Retrofit getRetrofit() {
        return retrofit;
    }

    @NonNull
    public WeatherService getWeatherService() {
        return weatherService;
    }
}
