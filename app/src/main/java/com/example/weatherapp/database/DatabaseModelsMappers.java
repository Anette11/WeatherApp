package com.example.weatherapp.database;

import com.example.weatherapp.database.models.HourlyDbo;
import com.example.weatherapp.models.Hourly;

public class DatabaseModelsMappers {

    public static HourlyDbo fromHourlyToHourlyDbo(
            Hourly hourly
    ) {
        return new HourlyDbo(
                hourly.getHumidity(),
                hourly.getTemperature(),
                hourly.getTime(),
                hourly.getWeatherCode(),
                hourly.getWindSpeed()
        );
    }

    public static Hourly fromHourlyDboToHourly(
            HourlyDbo hourlyDbo
    ) {
        return new Hourly(
                hourlyDbo.getHumidity(),
                hourlyDbo.getTemperature(),
                hourlyDbo.getTime(),
                hourlyDbo.getWeatherCode(),
                hourlyDbo.getWindSpeed()
        );
    }
}