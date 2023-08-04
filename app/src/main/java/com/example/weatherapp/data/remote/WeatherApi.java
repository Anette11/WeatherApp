package com.example.weatherapp.data.remote;

import android.database.Observable;

import retrofit2.Response;
import retrofit2.http.GET;

public interface WeatherApi {

    static final String BASE_URL = "https://api.open-meteo.com/";

    @GET("v1/forecast?hourly=temperature_2m,relativehumidity_2m,weathercode,windspeed_10m")
    Observable<Response<GetWeatherResponseDto>> getWeather();
}