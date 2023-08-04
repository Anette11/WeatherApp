package com.example.weatherapp.domain.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Hourly {
    @SerializedName("relativehumidity_2m")
    List<Integer> relativeHumidity2m;
    @SerializedName("temperature_2m")
    List<Double> temperature2m;
    List<String> time;
    @SerializedName("weathercode")
    List<Integer> weatherCode;
    @SerializedName("windspeed_10m")
    List<Double> windSpeed10m;

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

    public void setRelativeHumidity2m(List<Integer> relativeHumidity2m) {
        this.relativeHumidity2m = relativeHumidity2m;
    }

    public List<Double> getTemperature2m() {
        return temperature2m;
    }

    public void setTemperature2m(List<Double> temperature2m) {
        this.temperature2m = temperature2m;
    }

    public List<String> getTime() {
        return time;
    }

    public void setTime(List<String> time) {
        this.time = time;
    }

    public List<Integer> getWeatherCode() {
        return weatherCode;
    }

    public void setWeatherCode(List<Integer> weatherCode) {
        this.weatherCode = weatherCode;
    }

    public List<Double> getWindSpeed10m() {
        return windSpeed10m;
    }

    public void setWindSpeed10m(List<Double> windSpeed10m) {
        this.windSpeed10m = windSpeed10m;
    }
}