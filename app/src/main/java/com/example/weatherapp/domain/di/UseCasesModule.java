package com.example.weatherapp.domain.di;

import com.example.weatherapp.domain.repository.GetWeatherRepository;
import com.example.weatherapp.domain.use_case.GetWeatherUseCase;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class UseCasesModule {

    @Provides
    GetWeatherUseCase provideGetWeatherUseCase(GetWeatherRepository repository) {
        return new GetWeatherUseCase(repository);
    }
}