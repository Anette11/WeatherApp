package com.example.weatherapp.utils.formatters;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class DateFormatterModule {

    @Provides
    DateFormatter provideDateFormatter() {
        return new DateFormatter();
    }
}