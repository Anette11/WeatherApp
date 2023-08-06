package com.example.weatherapp.data.remote.dto;

import com.google.gson.annotations.SerializedName;

public class HourlyUnitsDto {
    @SerializedName("relativehumidity_2m")
    private final String relativeHumidity2m;
    @SerializedName("temperature_2m")
    private final String temperature2m;
    private final String time;
    @SerializedName("weathercode")
    private final String weatherCode;
    @SerializedName("windspeed_10m")
    private final String windSpeed10m;

    public HourlyUnitsDto(
            String relativeHumidity2m,
            String temperature2m,
            String time,
            String weatherCode,
            String windSpeed10m
    ) {
        this.relativeHumidity2m = relativeHumidity2m;
        this.temperature2m = temperature2m;
        this.time = time;
        this.weatherCode = weatherCode;
        this.windSpeed10m = windSpeed10m;
    }

    public String getRelativeHumidity2m() {
        return relativeHumidity2m;
    }

    public String getTemperature2m() {
        return temperature2m;
    }

    public String getTime() {
        return time;
    }

    public String getWeatherCode() {
        return weatherCode;
    }

    public String getWindSpeed10m() {
        return windSpeed10m;
    }
}