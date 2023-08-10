package com.example.weatherapp.presentation.adapter.items;

import androidx.annotation.DrawableRes;

public class MainInfoItem extends WeatherItem {

    private final String cityName;
    @DrawableRes
    private final int image;
    private final String temperature;
    private final String windSpeed;
    private final String humidity;
    private final String description;
    private final double latitude;
    private final double longitude;

    public MainInfoItem(
            String cityName,
            int image,
            String temperature,
            String windSpeed,
            String humidity,
            String description,
            double latitude,
            double longitude
    ) {
        this.cityName = cityName;
        this.image = image;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.humidity = humidity;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getCityName() {
        return cityName;
    }

    public int getImage() {
        return image;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getDescription() {
        return description;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public int compareTo(WeatherItem weatherItem) {
        return ((MainInfoItem) weatherItem).cityName.compareTo(this.cityName);
    }
}