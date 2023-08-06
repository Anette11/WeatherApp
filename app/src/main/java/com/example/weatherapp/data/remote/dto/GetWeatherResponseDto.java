package com.example.weatherapp.data.remote.dto;

import com.google.gson.annotations.SerializedName;

public class GetWeatherResponseDto {
    private final int elevation;
    @SerializedName("generationtime_ms")
    private final double generationTimeMs;
    private final HourlyDto hourly;
    @SerializedName("hourly_units")
    private final HourlyUnitsDto hourlyUnits;
    private final double latitude;
    private final double longitude;
    private final String timezone;
    @SerializedName("timezone_abbreviation")
    private final String timezoneAbbreviation;
    @SerializedName("utc_offset_seconds")
    private final int utcOffsetSeconds;

    public GetWeatherResponseDto(
            int elevation,
            double generationTimeMs,
            HourlyDto hourly,
            HourlyUnitsDto hourlyUnits,
            double latitude,
            double longitude,
            String timezone,
            String timezoneAbbreviation,
            int utcOffsetSeconds
    ) {
        this.elevation = elevation;
        this.generationTimeMs = generationTimeMs;
        this.hourly = hourly;
        this.hourlyUnits = hourlyUnits;
        this.latitude = latitude;
        this.longitude = longitude;
        this.timezone = timezone;
        this.timezoneAbbreviation = timezoneAbbreviation;
        this.utcOffsetSeconds = utcOffsetSeconds;
    }

    public int getElevation() {
        return elevation;
    }

    public double getGenerationTimeMs() {
        return generationTimeMs;
    }

    public HourlyDto getHourly() {
        return hourly;
    }

    public HourlyUnitsDto getHourlyUnits() {
        return hourlyUnits;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getTimezone() {
        return timezone;
    }

    public String getTimezoneAbbreviation() {
        return timezoneAbbreviation;
    }

    public int getUtcOffsetSeconds() {
        return utcOffsetSeconds;
    }
}