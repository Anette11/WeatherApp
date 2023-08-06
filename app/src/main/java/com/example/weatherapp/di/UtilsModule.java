package com.example.weatherapp.di;

import com.example.weatherapp.presentation.utils.DateFormatter;
import com.example.weatherapp.presentation.utils.LocationCoordinatesContainer;
import com.example.weatherapp.presentation.utils.StringFormatter;

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

    @Provides
    DateFormatter provideDateFormatter() {
        return new DateFormatter();
    }

    @Provides
    StringFormatter provideStringFormatter() {
        return new StringFormatter();
    }
}