package com.example.weatherapp.domain.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Hourly {
    @SerializedName("relativehumidity_2m")
    private final List<Integer> relativeHumidity2m;
    @SerializedName("temperature_2m")
    private final List<Double> temperature2m;
    private final List<String> time;
    @SerializedName("weathercode")
    private final List<Integer> weatherCode;
    @SerializedName("windspeed_10m")
    private final List<Double> windSpeed10m;

    public Hourly(
            List<Integer> relativeHumidity2m,
            List<Double> temperature2m,
            List<String> time,
            List<Integer> weatherCode,
            List<Double> windSpeed10m
    ) {
        this.relativeHumidity2m = relativeHumidity2m;
        this.temperature2m = temperature2m;
        this.time = time;
        this.weatherCode = weatherCode;
        this.windSpeed10m = windSpeed10m;
    }

    public List<Integer> getRelativeHumidity2m() {
        return relativeHumidity2m;
    }

    public List<Double> getTemperature2m() {
        return temperature2m;
    }

    public List<String> getTime() {
        return time;
    }

    public List<Integer> getWeatherCode() {
        return weatherCode;
    }

    public List<Double> getWindSpeed10m() {
        return windSpeed10m;
    }
}