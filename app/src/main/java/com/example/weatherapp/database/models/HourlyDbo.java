package com.example.weatherapp.database.models;

import java.util.List;

public class HourlyDbo {

    private final List<Integer> humidity;
    private final List<Double> temperature;
    private final List<String> time;
    private final List<Integer> weatherCode;
    private final List<Double> windSpeed;

    public HourlyDbo(
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