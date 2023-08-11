package com.example.weatherapp.models.weather;

public class Weather {
    private final Hourly hourly;

    public Weather(Hourly hourly) {
        this.hourly = hourly;
    }

    public Hourly getHourly() {
        return hourly;
    }
}