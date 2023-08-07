package com.example.weatherapp.data.remote.dto;

public class WeatherDto {
    private final HourlyDto hourly;

    public WeatherDto(HourlyDto hourly) {
        this.hourly = hourly;
    }

    public HourlyDto getHourly() {
        return hourly;
    }
}