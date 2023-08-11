package com.example.weatherapp.api;

import com.example.weatherapp.api.models.HourlyDto;
import com.example.weatherapp.api.models.WeatherDto;
import com.example.weatherapp.models.Hourly;
import com.example.weatherapp.models.Weather;

public class ApiModelsMappers {

    public static Weather fromWeatherDtoToWeather(
            WeatherDto weatherDto
    ) {
        return new Weather(fromHourlyDtoToHourly(weatherDto.getHourly()));
    }

    private static Hourly fromHourlyDtoToHourly(
            HourlyDto hourlyDto
    ) {
        return new Hourly(
                hourlyDto.getHumidity(),
                hourlyDto.getTemperature(),
                hourlyDto.getTime(),
                hourlyDto.getWeatherCode(),
                hourlyDto.getWindSpeed()
        );
    }
}