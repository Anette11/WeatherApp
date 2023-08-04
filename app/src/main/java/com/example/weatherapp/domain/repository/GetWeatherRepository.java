package com.example.weatherapp.domain.repository;

import android.database.Observable;

import com.example.weatherapp.data.remote.GetWeatherResponseDto;

import retrofit2.Response;

public interface GetWeatherRepository {

    Observable<Response<GetWeatherResponseDto>> getWeather();
}