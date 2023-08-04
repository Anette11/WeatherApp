package com.example.weatherapp.domain.data;

import com.google.gson.annotations.SerializedName;

public class HourlyUnits {
    @SerializedName("relativehumidity_2m")
    String relativeHumidity2m;
    @SerializedName("temperature_2m")
    String temperature2m;
    String time;
    @SerializedName("weathercode")
    String weatherCode;
    @SerializedName("windspeed_10m")
    String windSpeed10m;

    public HourlyUnits(
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

    public void setRelativeHumidity2m(String relativeHumidity2m) {
        this.relativeHumidity2m = relativeHumidity2m;
    }

    public String getTemperature2m() {
        return temperature2m;
    }

    public void setTemperature2m(String temperature2m) {
        this.temperature2m = temperature2m;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWeatherCode() {
        return weatherCode;
    }

    public void setWeatherCode(String weatherCode) {
        this.weatherCode = weatherCode;
    }

    public String getWindSpeed10m() {
        return windSpeed10m;
    }

    public void setWindSpeed10m(String windSpeed10m) {
        this.windSpeed10m = windSpeed10m;
    }
}