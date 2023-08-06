package com.example.weatherapp.data.remote.dto;

public class GetWeatherResponseDto {
    private final HourlyDto hourly;

    public GetWeatherResponseDto(HourlyDto hourly) {
        this.hourly = hourly;
    }

    public HourlyDto getHourly() {
        return hourly;
    }
}