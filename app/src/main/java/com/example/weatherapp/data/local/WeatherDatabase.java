package com.example.weatherapp.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.weatherapp.data.local.dbo.HourlyDbo;

@Database(
        entities = {HourlyDbo.class},
        version = 1
)
public abstract class WeatherDatabase extends RoomDatabase {

    public abstract WeatherDao weatherDao();
}