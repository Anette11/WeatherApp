package com.example.weatherapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class WeatherDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "weather_db";

    public WeatherDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(
            SQLiteDatabase db,
            int oldVersion,
            int newVersion
    ) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + WeatherDbContract.HourlyEntry.TABLE_NAME + " (" +
                    WeatherDbContract.HourlyEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    WeatherDbContract.HourlyEntry.COLUMN_NAME_HUMIDITY + " TEXT," +
                    WeatherDbContract.HourlyEntry.COLUMN_NAME_TEMPERATURE + " TEXT," +
                    WeatherDbContract.HourlyEntry.COLUMN_NAME_TIME + " TEXT," +
                    WeatherDbContract.HourlyEntry.COLUMN_NAME_WEATHER_CODE + " TEXT," +
                    WeatherDbContract.HourlyEntry.COLUMN_NAME_WIND_SPEED + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + WeatherDbContract.HourlyEntry.TABLE_NAME;
}