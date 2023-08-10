package com.example.weatherapp.data.local;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.weatherapp.data.local.converters.DoubleConverter;
import com.example.weatherapp.data.local.converters.IntegerConverter;
import com.example.weatherapp.data.local.converters.StringConverter;
import com.example.weatherapp.data.local.dbo.HourlyDbo;

import java.util.List;

public class WeatherDbManager {

    private final WeatherDbHelper weatherDbHelper;
    private final String KEY_HUMIDITY = WeatherDatabaseContract.HourlyEntry.COLUMN_NAME_HUMIDITY;
    private final String KEY_TEMPERATURE = WeatherDatabaseContract.HourlyEntry.COLUMN_NAME_TEMPERATURE;
    private final String KEY_TIME = WeatherDatabaseContract.HourlyEntry.COLUMN_NAME_TIME;
    private final String KEY_WEATHER_CODE = WeatherDatabaseContract.HourlyEntry.COLUMN_NAME_WEATHER_CODE;
    private final String KEY_WIND_SPEED = WeatherDatabaseContract.HourlyEntry.COLUMN_NAME_WIND_SPEED;
    private final IntegerConverter integerConverter = new IntegerConverter();
    private final DoubleConverter doubleConverter = new DoubleConverter();
    private final StringConverter stringConverter = new StringConverter();

    public WeatherDbManager(WeatherDbHelper weatherDbHelper) {
        this.weatherDbHelper = weatherDbHelper;
    }

    public void saveHourly(HourlyDbo hourlyDbo) {
        ContentValues contentValues = new ContentValues();
        String humidity = integerConverter.fromListToString(hourlyDbo.getHumidity());
        String temperature = doubleConverter.fromListToString(hourlyDbo.getTemperature());
        String time = stringConverter.fromListToString(hourlyDbo.getTime());
        String weatherCode = integerConverter.fromListToString(hourlyDbo.getWeatherCode());
        String windSpeed = doubleConverter.fromListToString(hourlyDbo.getWindSpeed());
        contentValues.put(KEY_HUMIDITY, humidity);
        contentValues.put(KEY_TEMPERATURE, temperature);
        contentValues.put(KEY_TIME, time);
        contentValues.put(KEY_WEATHER_CODE, weatherCode);
        contentValues.put(KEY_WIND_SPEED, windSpeed);
        SQLiteDatabase sqLiteDatabase = weatherDbHelper.getWritableDatabase();
        sqLiteDatabase.insert(WeatherDbHelper.DATABASE_NAME, null, contentValues);
        sqLiteDatabase.close();
    }

    public HourlyDbo getHourly() {
        int limit = 1;
        String sql = "SELECT * from " + WeatherDatabaseContract.HourlyEntry.TABLE_NAME + " LIMIT " + limit;
        SQLiteDatabase sqLiteDatabase = weatherDbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        if (cursor == null || cursor.getCount() != limit) return null;
        boolean isMoveSucceeded = cursor.moveToFirst();
        if (!isMoveSucceeded) return null;
        int columnIndexHumidity = cursor.getColumnIndex(KEY_HUMIDITY);
        int columnIndexTemperature = cursor.getColumnIndex(KEY_TEMPERATURE);
        int columnIndexTime = cursor.getColumnIndex(KEY_TIME);
        int columnIndexWeatherCode = cursor.getColumnIndex(KEY_WEATHER_CODE);
        int columnIndexWindSpeed = cursor.getColumnIndex(KEY_WIND_SPEED);
        String humidityStr = cursor.getString(columnIndexHumidity);
        String temperatureStr = cursor.getString(columnIndexTemperature);
        String timeStr = cursor.getString(columnIndexTime);
        String weatherCodeStr = cursor.getString(columnIndexWeatherCode);
        String windSpeedStr = cursor.getString(columnIndexWindSpeed);
        List<Integer> humidity = integerConverter.fromStringToList(humidityStr);
        List<Double> temperature = doubleConverter.fromStringToList(temperatureStr);
        List<String> time = stringConverter.fromStringToList(timeStr);
        List<Integer> weatherCode = integerConverter.fromStringToList(weatherCodeStr);
        List<Double> windSpeed = doubleConverter.fromStringToList(windSpeedStr);
        cursor.close();
        sqLiteDatabase.close();
        return new HourlyDbo(humidity, temperature, time, weatherCode, windSpeed);
    }

    public void deleteAllHourly() {
        String sql = "DELETE FROM " + WeatherDatabaseContract.HourlyEntry.TABLE_NAME;
        SQLiteDatabase sqLiteDatabase = weatherDbHelper.getWritableDatabase();
        sqLiteDatabase.execSQL(sql);
        sqLiteDatabase.close();
    }
}