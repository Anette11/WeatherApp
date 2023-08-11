package com.example.weatherapp.database;

import android.content.Context;

import com.example.weatherapp.database.WeatherDbHelper;
import com.example.weatherapp.database.WeatherDbManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class DatabaseModule {

    @Provides
    @Singleton
    WeatherDbHelper provideWeatherDbHelper(@ApplicationContext Context context) {
        return new WeatherDbHelper(context);
    }

    @Provides
    @Singleton
    WeatherDbManager provideWeatherDbManager(WeatherDbHelper weatherDbHelper) {
        return new WeatherDbManager(weatherDbHelper);
    }
}