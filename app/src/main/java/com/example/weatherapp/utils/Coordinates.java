package com.example.weatherapp.utils;

public class Coordinates {

    private final double latitude;
    private final double longitude;
    private final String cityName;

    public Coordinates(
            double latitude,
            double longitude,
            String cityName
    ) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.cityName = cityName;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getCityName() {
        return cityName;
    }
}