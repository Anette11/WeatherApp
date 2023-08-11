package com.example.weatherapp.utils.formatters;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class StringFormatterModule {

    @Provides
    StringFormatter provideStringFormatter() {
        return new StringFormatter();
    }
}