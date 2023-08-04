package com.example.weatherapp.data.remote;

import com.google.gson.annotations.SerializedName;

public class GetWeatherResponseDto {
    private int elevation;
    @SerializedName("generationtime_ms")
    private double generationTimeMs;
    private HourlyDto hourly;
    @SerializedName("hourly_units")
    private HourlyUnitsDto hourlyUnits;
    private double latitude;
    private double longitude;
    private String timezone;
    @SerializedName("timezone_abbreviation")
    private String timezoneAbbreviation;
    @SerializedName("utc_offset_seconds")
    private int utcOffsetSeconds;

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

    public void setElevation(int elevation) {
        this.elevation = elevation;
    }

    public double getGenerationTimeMs() {
        return generationTimeMs;
    }

    public void setGenerationTimeMs(double generationTimeMs) {
        this.generationTimeMs = generationTimeMs;
    }

    public HourlyDto getHourly() {
        return hourly;
    }

    public void setHourly(HourlyDto hourly) {
        this.hourly = hourly;
    }

    public HourlyUnitsDto getHourlyUnits() {
        return hourlyUnits;
    }

    public void setHourlyUnits(HourlyUnitsDto hourlyUnits) {
        this.hourlyUnits = hourlyUnits;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getTimezoneAbbreviation() {
        return timezoneAbbreviation;
    }

    public void setTimezoneAbbreviation(String timezoneAbbreviation) {
        this.timezoneAbbreviation = timezoneAbbreviation;
    }

    public int getUtcOffsetSeconds() {
        return utcOffsetSeconds;
    }

    public void setUtcOffsetSeconds(int utcOffsetSeconds) {
        this.utcOffsetSeconds = utcOffsetSeconds;
    }
}