package com.example.weatherapp.di;

import com.example.weatherapp.data.repository.GetWeatherRepositoryImpl;
import com.example.weatherapp.domain.repository.GetWeatherRepository;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public abstract class RepositoryModule {

    @Binds
    abstract GetWeatherRepository bindGetWeatherRepository(GetWeatherRepositoryImpl repository);
}