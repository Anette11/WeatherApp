package com.example.weatherapp.utils.coordinates;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class CoordinatesModule {

    @Provides
    @Singleton
    LocationCoordinatesContainer provideLocationCoordinatesContainer() {
        return new LocationCoordinatesContainer();
    }
}