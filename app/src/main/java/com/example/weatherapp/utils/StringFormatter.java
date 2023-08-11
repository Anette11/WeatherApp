package com.example.weatherapp.utils;

public class StringFormatter {

    public String formatToTemperature(Double temperature) {
        return temperature + " ℃";
    }

    public String formatToWindSpeed(Double windSpeed) {
        return windSpeed + " km/h";
    }

    public String formatToHumidity(Integer humidity) {
        return humidity + " %";
    }
}