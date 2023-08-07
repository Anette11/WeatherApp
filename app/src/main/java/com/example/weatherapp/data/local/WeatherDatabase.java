package com.example.weatherapp.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.weatherapp.data.local.converters.DoubleConverter;
import com.example.weatherapp.data.local.converters.IntegerConverter;
import com.example.weatherapp.data.local.converters.StringConverter;
import com.example.weatherapp.data.local.dbo.HourlyDbo;

@Database(
        entities = {HourlyDbo.class},
        version = 1,
        exportSchema = false
)
@TypeConverters({
        IntegerConverter.class,
        DoubleConverter.class,
        StringConverter.class
})
public abstract class WeatherDatabase extends RoomDatabase {

    public abstract WeatherDao weatherDao();
}