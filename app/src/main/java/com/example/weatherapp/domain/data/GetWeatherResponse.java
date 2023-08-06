package com.example.weatherapp.domain.data;

public class GetWeatherResponse {
    private final Hourly hourly;

    public GetWeatherResponse(Hourly hourly) {
        this.hourly = hourly;
    }

    public Hourly getHourly() {
        return hourly;
    }
}