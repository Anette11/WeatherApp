package com.example.weatherapp.data.remote.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HourlyDto {
    @SerializedName("relativehumidity_2m")
    private final List<Integer> humidity;
    @SerializedName("temperature_2m")
    private final List<Double> temperature;
    private final List<String> time;
    @SerializedName("weathercode")
    private final List<Integer> weatherCode;
    @SerializedName("windspeed_10m")
    private final List<Double> windSpeed;

    public HourlyDto(
            List<Integer> humidity,
            List<Double> temperature,
            List<String> time,
            List<Integer> weatherCode,
            List<Double> windSpeed
    ) {
        this.humidity = humidity;
        this.temperature = temperature;
        this.time = time;
        this.weatherCode = weatherCode;
        this.windSpeed = windSpeed;
    }

    public List<Integer> getHumidity() {
        return humidity;
    }

    public List<Double> getTemperature() {
        return temperature;
    }

    public List<String> getTime() {
        return time;
    }

    public List<Integer> getWeatherCode() {
        return weatherCode;
    }

    public List<Double> getWindSpeed() {
        return windSpeed;
    }
}