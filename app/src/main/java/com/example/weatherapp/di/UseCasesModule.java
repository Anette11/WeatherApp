package com.example.weatherapp.di;

import com.example.weatherapp.domain.repository.WeatherRepository;
import com.example.weatherapp.domain.use_case.GetWeatherFromDbUseCase;
import com.example.weatherapp.domain.use_case.RefreshWeatherUseCase;
import com.example.weatherapp.presentation.utils.DateFormatter;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class UseCasesModule {

    @Provides
    RefreshWeatherUseCase provideRefreshWeatherUseCase(
            WeatherRepository repository,
            DateFormatter dateFormatter
    ) {
        return new RefreshWeatherUseCase(repository, dateFormatter);
    }

    @Provides
    GetWeatherFromDbUseCase provideGetWeatherFromDbUseCase(
            WeatherRepository repository
    ) {
        return new GetWeatherFromDbUseCase(repository);
    }
}