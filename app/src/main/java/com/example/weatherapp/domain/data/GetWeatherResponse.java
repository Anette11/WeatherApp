package com.example.weatherapp.domain.data;

import com.google.gson.annotations.SerializedName;

public class GetWeatherResponse {
    private int elevation;
    @SerializedName("generationtime_ms")
    private double generationTimeMs;
    private Hourly hourly;
    @SerializedName("hourly_units")
    private HourlyUnits hourlyUnits;
    private double latitude;
    private double longitude;
    private String timezone;
    @SerializedName("timezone_abbreviation")
    private String timezoneAbbreviation;
    @SerializedName("utc_offset_seconds")
    private int utcOffsetSeconds;

    public GetWeatherResponse(
            int elevation,
            double generationTimeMs,
            Hourly hourly,
            HourlyUnits hourlyUnits,
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

    public Hourly getHourly() {
        return hourly;
    }

    public void setHourly(Hourly hourly) {
        this.hourly = hourly;
    }

    public HourlyUnits getHourlyUnits() {
        return hourlyUnits;
    }

    public void setHourlyUnits(HourlyUnits hourlyUnits) {
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