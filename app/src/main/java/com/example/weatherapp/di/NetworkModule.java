package com.example.weatherapp.di;

import com.example.weatherapp.BuildConfig;
import com.example.weatherapp.data.remote.WeatherApi;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
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
            @Named("baseUrl") String baseUrl,
            OkHttpClient okHttpClient
    ) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
                .create(WeatherApi.class);
    }

    @Provides
    HttpLoggingInterceptor provideHttpLoggingInterceptor(
    ) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        return httpLoggingInterceptor;
    }

    @Provides
    OkHttpClient provideOkHttpClient(
            HttpLoggingInterceptor httpLoggingInterceptor
    ) {
        return new OkHttpClient.Builder().
                addInterceptor(httpLoggingInterceptor)
                .build();
    }
}