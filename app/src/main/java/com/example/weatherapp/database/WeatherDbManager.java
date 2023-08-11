package com.example.weatherapp.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.weatherapp.database.converters.DoubleConverter;
import com.example.weatherapp.database.converters.IntegerConverter;
import com.example.weatherapp.database.converters.StringConverter;
import com.example.weatherapp.database.models.HourlyDbo;

import java.util.List;

public class WeatherDbManager {

    private final WeatherDbHelper weatherDbHelper;
    private final String KEY_HUMIDITY = WeatherDbContract.HourlyEntry.COLUMN_NAME_HUMIDITY;
    private final String KEY_TEMPERATURE = WeatherDbContract.HourlyEntry.COLUMN_NAME_TEMPERATURE;
    private final String KEY_TIME = WeatherDbContract.HourlyEntry.COLUMN_NAME_TIME;
    private final String KEY_WEATHER_CODE = WeatherDbContract.HourlyEntry.COLUMN_NAME_WEATHER_CODE;
    private final String KEY_WIND_SPEED = WeatherDbContract.HourlyEntry.COLUMN_NAME_WIND_SPEED;
    private final IntegerConverter integerConverter = new IntegerConverter();
    private final DoubleConverter doubleConverter = new DoubleConverter();
    private final StringConverter stringConverter = new StringConverter();
    private final MutableLiveData<HourlyDbo> hourlyLiveData = new MutableLiveData<>(null);

    public LiveData<HourlyDbo> getHourlyLiveData() {
        return hourlyLiveData;
    }

    public WeatherDbManager(WeatherDbHelper weatherDbHelper) {
        this.weatherDbHelper = weatherDbHelper;
    }

    private void saveHourly(
            HourlyDbo hourlyDbo,
            SQLiteDatabase sqLiteDatabase
    ) {
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
        sqLiteDatabase.insertWithOnConflict(
                WeatherDbContract.HourlyEntry.TABLE_NAME,
                null,
                contentValues,
                SQLiteDatabase.CONFLICT_REPLACE
        );
    }

    private HourlyDbo getHourly() {
        int limit = 1;
        String sql = "SELECT * from " + WeatherDbContract.HourlyEntry.TABLE_NAME + " LIMIT " + limit;
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
        cursor.close();
        sqLiteDatabase.close();
        List<Integer> humidity = integerConverter.fromStringToList(humidityStr);
        List<Double> temperature = doubleConverter.fromStringToList(temperatureStr);
        List<String> time = stringConverter.fromStringToList(timeStr);
        List<Integer> weatherCode = integerConverter.fromStringToList(weatherCodeStr);
        List<Double> windSpeed = doubleConverter.fromStringToList(windSpeedStr);
        return new HourlyDbo(humidity, temperature, time, weatherCode, windSpeed);
    }

    private void clearHourly(
            SQLiteDatabase sqLiteDatabase
    ) {
        String sql = "DELETE FROM " + WeatherDbContract.HourlyEntry.TABLE_NAME;
        sqLiteDatabase.execSQL(sql);
    }

    public void refreshWeather(
            HourlyDbo hourlyDbo
    ) {
        SQLiteDatabase sqLiteDatabase = weatherDbHelper.getWritableDatabase();
        sqLiteDatabase.beginTransaction();
        try {
            clearHourly(sqLiteDatabase);
            saveHourly(hourlyDbo, sqLiteDatabase);
            sqLiteDatabase.setTransactionSuccessful();
        } finally {
            sqLiteDatabase.endTransaction();
            sqLiteDatabase.close();
        }
        hourlyLiveData.postValue(getHourly());
    }
}