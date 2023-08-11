package com.example.weatherapp.database;

import android.provider.BaseColumns;

public final class WeatherDbContract {

    private WeatherDbContract() {
    }

    public static class HourlyEntry implements BaseColumns {
        public static final String TABLE_NAME = "table_hourly";
        public static final String COLUMN_NAME_HUMIDITY = "humidity";
        public static final String COLUMN_NAME_TEMPERATURE = "temperature";
        public static final String COLUMN_NAME_TIME = "time";
        public static final String COLUMN_NAME_WEATHER_CODE = "weather_code";
        public static final String COLUMN_NAME_WIND_SPEED = "wind_speed";
    }
}