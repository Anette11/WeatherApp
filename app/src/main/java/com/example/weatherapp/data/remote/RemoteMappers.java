package com.example.weatherapp.data.remote;

import com.example.weatherapp.data.remote.dto.GetWeatherResponseDto;
import com.example.weatherapp.data.remote.dto.HourlyDto;
import com.example.weatherapp.data.remote.dto.HourlyUnitsDto;
import com.example.weatherapp.domain.data.GetWeatherResponse;
import com.example.weatherapp.domain.data.Hourly;
import com.example.weatherapp.domain.data.HourlyUnits;

public class RemoteMappers {

    public static GetWeatherResponse fromGetWeatherResponseDtoToGetWeatherResponse(
            GetWeatherResponseDto getWeatherResponseDto
    ) {
        return new GetWeatherResponse(
                getWeatherResponseDto.getElevation(),
                getWeatherResponseDto.getGenerationTimeMs(),
                fromHourlyDtoToHourly(getWeatherResponseDto.getHourly()),
                fromHourlyUnitsDtoToHourlyUnits(getWeatherResponseDto.getHourlyUnits()),
                getWeatherResponseDto.getLatitude(),
                getWeatherResponseDto.getLongitude(),
                getWeatherResponseDto.getTimezone(),
                getWeatherResponseDto.getTimezoneAbbreviation(),
                getWeatherResponseDto.getUtcOffsetSeconds()
        );
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

    private static HourlyUnits fromHourlyUnitsDtoToHourlyUnits(
            HourlyUnitsDto hourlyUnitsDto
    ) {
        return new HourlyUnits(
                hourlyUnitsDto.getRelativeHumidity2m(),
                hourlyUnitsDto.getTemperature2m(),
                hourlyUnitsDto.getTime(),
                hourlyUnitsDto.getWeatherCode(),
                hourlyUnitsDto.getWindSpeed10m()
        );
    }
}