package com.example.weatherapp.repositories;

import com.example.weatherapp.repositories.interfaces.WeatherRepository;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public abstract class RepositoryModule {

    @Binds
    abstract WeatherRepository bindWeatherRepository(WeatherRepositoryImpl repository);
}