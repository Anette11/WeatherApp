package com.example.weatherapp.data.remote;

import com.example.weatherapp.data.remote.dto.GetWeatherResponseDto;
import com.example.weatherapp.data.remote.dto.HourlyDto;
import com.example.weatherapp.domain.data.GetWeatherResponse;
import com.example.weatherapp.domain.data.Hourly;

public class RemoteMappers {

    public static GetWeatherResponse fromGetWeatherResponseDtoToGetWeatherResponse(
            GetWeatherResponseDto getWeatherResponseDto
    ) {
        return new GetWeatherResponse(fromHourlyDtoToHourly(getWeatherResponseDto.getHourly()));
    }

    private static Hourly fromHourlyDtoToHourly(
            HourlyDto hourlyDto
    ) {
        return new Hourly(
                hourlyDto.getRelativeHumidity2m(),
                hourlyDto.getTemperature2m(),
                hourlyDto.getTime(),
                hourlyDto.getWeatherCode(),
                hourlyDto.getWindSpeed10m()
        );
    }
}