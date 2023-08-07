package com.example.weatherapp.data.local.dbo;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "table_hourly")
public class HourlyDbo {

    @PrimaryKey(autoGenerate = true)
    private final int id;

    private final List<Integer> humidity;
    private final List<Double> temperature;
    private final List<String> time;
    private final List<Integer> weatherCode;
    private final List<Double> windSpeed;

    public HourlyDbo(
            int id,
            List<Integer> humidity,
            List<Double> temperature,
            List<String> time,
            List<Integer> weatherCode,
            List<Double> windSpeed
    ) {
        this.id = id;
        this.humidity = humidity;
        this.temperature = temperature;
        this.time = time;
        this.weatherCode = weatherCode;
        this.windSpeed = windSpeed;
    }

    public int getId() {
        return id;
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