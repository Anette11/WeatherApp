package com.example.weatherapp.data.remote;

import com.example.weatherapp.data.remote.dto.WeatherDto;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {

    @GET("v1/forecast?hourly=temperature_2m,relativehumidity_2m,weathercode,windspeed_10m")
    Single<WeatherDto> getWeather(
            @Query("latitude") double latitude,
            @Query("longitude") double longitude,
            @Query("timezone") String timezone
    );
}