package com.example.weatherapp.di;

import com.example.weatherapp.domain.repository.WeatherRepository;
import com.example.weatherapp.domain.use_case.GetWeatherUseCase;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class UseCasesModule {

    @Provides
    GetWeatherUseCase provideGetWeatherUseCase(WeatherRepository repository) {
        return new GetWeatherUseCase(repository);
    }
}