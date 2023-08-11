package com.example.weatherapp.models;

public class Weather {
    private final Hourly hourly;

    public Weather(Hourly hourly) {
        this.hourly = hourly;
    }

    public Hourly getHourly() {
        return hourly;
    }
}