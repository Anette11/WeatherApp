package com.example.weatherapp.use_cases;

import com.example.weatherapp.repositories.interfaces.WeatherRepository;
import com.example.weatherapp.utils.formatters.DateFormatter;

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