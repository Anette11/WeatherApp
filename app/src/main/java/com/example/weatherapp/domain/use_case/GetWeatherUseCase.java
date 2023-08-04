package com.example.weatherapp.domain.use_case;

import android.database.Observable;

import com.example.weatherapp.data.remote.dto.GetWeatherResponseDto;
import com.example.weatherapp.domain.repository.GetWeatherRepository;

import javax.inject.Inject;

import retrofit2.Response;

public class GetWeatherUseCase {

    private final GetWeatherRepository repository;

    @Inject
    public GetWeatherUseCase(GetWeatherRepository repository) {
        this.repository = repository;
    }

    public Observable<Response<GetWeatherResponseDto>> execute() {
        return repository.getWeather();
    }
}