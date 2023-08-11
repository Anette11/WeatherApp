package com.example.weatherapp.utils;

import com.example.weatherapp.utils.formatters.DateFormatter;
import com.example.weatherapp.utils.formatters.StringFormatter;

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

    @Provides
    ErrorMessageContainer provideErrorMessageContainer() {
        return new ErrorMessageContainer();
    }
}