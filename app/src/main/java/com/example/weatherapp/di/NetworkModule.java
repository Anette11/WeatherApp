package com.example.weatherapp.di;

import com.example.weatherapp.data.remote.WeatherApi;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class NetworkModule {

    @Provides
    @Named("baseUrl")
    String provideBaseUrl() {
        return WeatherApi.BASE_URL;
    }

    @Provides
    @Singleton
    WeatherApi provideWeatherApi(
            @Named("baseUrl") String baseUrl
    ) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WeatherApi.class);
    }
}