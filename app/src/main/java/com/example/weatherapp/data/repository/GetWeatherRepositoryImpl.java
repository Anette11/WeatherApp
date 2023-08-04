package com.example.weatherapp.data.repository;

import android.database.Observable;

import com.example.weatherapp.data.remote.GetWeatherResponseDto;
import com.example.weatherapp.domain.repository.GetWeatherRepository;

import retrofit2.Response;

public class GetWeatherRepositoryImpl implements GetWeatherRepository {

    @Override
    public Observable<Response<GetWeatherResponseDto>> getWeather() {
        return null;
    }
}
