package com.example.weatherapp.data.local;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Upsert;

import com.example.weatherapp.data.local.dbo.HourlyDbo;

@Dao
public interface WeatherDao {

    @Upsert()
    void save(HourlyDbo hourlyDbo);

    @Query("DELETE FROM table_hourly")
    void deleteAll();
}