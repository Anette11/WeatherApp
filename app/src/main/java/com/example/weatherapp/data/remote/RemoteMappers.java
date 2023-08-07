package com.example.weatherapp.data.remote;

import com.example.weatherapp.data.remote.dto.HourlyDto;
import com.example.weatherapp.data.remote.dto.WeatherDto;
import com.example.weatherapp.domain.data.Hourly;
import com.example.weatherapp.domain.data.Weather;

public class RemoteMappers {

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