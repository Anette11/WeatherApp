package com.example.weatherapp.data.repository;

import android.database.Observable;

import com.example.weatherapp.data.remote.dto.GetWeatherResponseDto;
import com.example.weatherapp.domain.repository.GetWeatherRepository;

import javax.inject.Inject;

import retrofit2.Response;

public class GetWeatherRepositoryImpl implements GetWeatherRepository {

    @Inject
    public GetWeatherRepositoryImpl() {
    }

    @Override
    public Observable<Response<GetWeatherResponseDto>> getWeather() {
        return null;
    }
}
