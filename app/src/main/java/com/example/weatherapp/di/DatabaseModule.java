package com.example.weatherapp.di;

import android.content.Context;

import androidx.room.Room;

import com.example.weatherapp.data.local.WeatherDao;
import com.example.weatherapp.data.local.WeatherDatabase;
import com.example.weatherapp.data.local.WeatherDbHelper;

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
    WeatherDbHelper provideWeatherDbHelper(
            @ApplicationContext Context context
    ) {
        return new WeatherDbHelper(context);
    }

    @Provides
    @Singleton
    WeatherDatabase provideWeatherDatabase(
            @ApplicationContext Context context
    ) {
        return Room.databaseBuilder(
                        context,
                        WeatherDatabase.class,
                        "weather_db"
                )
                .build();
    }

    @Provides
    @Singleton
    WeatherDao provideWeatherDao(WeatherDatabase weatherDatabase) {
        return weatherDatabase.weatherDao();
    }
}