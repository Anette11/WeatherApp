package com.example.weatherapp.domain.data;

public class Weather {
    private final Hourly hourly;

    public Weather(Hourly hourly) {
        this.hourly = hourly;
    }

    public Hourly getHourly() {
        return hourly;
    }
}