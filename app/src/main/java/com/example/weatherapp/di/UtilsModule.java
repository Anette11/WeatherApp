package com.example.weatherapp.di;

import com.example.weatherapp.presentation.utils.LocationCoordinatesContainer;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class UtilsModule {

    @Provides
    @Singleton
    LocationCoordinatesContainer provideLocationCoordinatesContainer() {
        return new LocationCoordinatesContainer();
    }
}