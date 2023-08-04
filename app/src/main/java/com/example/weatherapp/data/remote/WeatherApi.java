package com.example.weatherapp.data.remote;

import com.example.weatherapp.data.remote.dto.GetWeatherResponseDto;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {

    String BASE_URL = "https://api.open-meteo.com/";

    @GET("v1/forecast?hourly=temperature_2m,relativehumidity_2m,weathercode,windspeed_10m")
    Single<GetWeatherResponseDto> getWeather(
            @Query("latitude") Double latitude,
            @Query("longitude") Double longitude
    );
}