package com.example.weatherapp.utils.error_message;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class ErrorMessageModule {

    @Provides
    ErrorMessageContainer provideErrorMessageContainer() {
        return new ErrorMessageContainer();
    }
}