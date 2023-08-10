package com.example.weatherapp.data.local;

import com.example.weatherapp.data.local.dbo.HourlyDbo;
import com.example.weatherapp.domain.data.Hourly;

public class LocalMappers {

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