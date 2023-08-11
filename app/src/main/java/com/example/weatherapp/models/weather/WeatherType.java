package com.example.weatherapp.models.weather;

import androidx.annotation.DrawableRes;

import com.example.weatherapp.R;

public enum WeatherType {

    CLEAR_SKY(
            "Clear sky",
            R.drawable.day_sunny_icon,
            0
    ),
    MAINLY_CLEAR(
            "Mainly clear",
            R.drawable.day_sunny_icon,
            1
    ),
    PARTLY_CLEAR(
            "Partly cloudy",
            R.drawable.day_cloudy_icon,
            2
    ),
    OVERCAST(
            "Overcast",
            R.drawable.cloud_icon,
            3
    ),
    FOG(
            "Fog",
            R.drawable.cloud_fog_icon,
            45
    ),
    DEPOSITING_RIME_FOG(
            "Depositing rime fog",
            R.drawable.cloud_fog_icon,
            48
    ),
    DRIZZLE_LIGHT(
            "Drizzle light",
            R.drawable.cloud_rain_icon,
            51
    ),
    DRIZZLE_MODERATE(
            "Drizzle moderate",
            R.drawable.cloud_rain_icon,
            53
    ),
    DRIZZLE_DENSE(
            "Drizzle dense",
            R.drawable.cloud_rain_icon,
            55
    ),
    FREEZING_DRIZZLE_LIGHT(
            "Freezing drizzle light",
            R.drawable.cloud_rain_icon,
            56
    ),
    FREEZING_DRIZZLE_DENSE(
            "Freezing drizzle dense",
            R.drawable.cloud_snow_icon,
            57
    ),
    RAIN_SLIGHT(
            "Rain slight",
            R.drawable.cloud_rain_icon,
            61
    ),
    RAIN_MODERATE(
            "Rain moderate",
            R.drawable.cloud_rain_icon,
            63
    ),
    RAIN_HEAVY(
            "Rain heavy",
            R.drawable.cloud_rain_icon,
            65
    ),
    FREEZING_RAIN_LIGHT(
            "Freezing rain light",
            R.drawable.cloud_snow_icon,
            66
    ),
    FREEZING_RAIN_HEAVY(
            "Freezing rain heavy",
            R.drawable.cloud_snow_icon,
            67
    ),
    SNOW_FALL_SLIGHT(
            "Snow fall slight",
            R.drawable.cloud_snow_icon,
            71
    ),
    SNOW_FALL_MODERATE(
            "Snow fall moderate",
            R.drawable.cloud_snow_icon,
            73
    ),
    SNOW_FALL_HEAVY(
            "Snow fall heavy",
            R.drawable.cloud_snow_icon,
            75
    ),
    SNOW_GRAINS(
            "Snow grains",
            R.drawable.cloud_snow_icon,
            77
    ),
    RAIN_SHOWERS_SLIGHT(
            "Rain showers slight",
            R.drawable.cloud_rain_icon,
            80
    ),
    RAIN_SHOWERS_MODERATE(
            "Rain showers moderate",
            R.drawable.cloud_rain_icon,
            81
    ),
    RAIN_SHOWERS_VIOLENT(
            "Rain showers violent",
            R.drawable.cloud_rain_icon,
            82
    ),
    SNOW_SHOWERS_SLIGHT(
            "Snow showers slight",
            R.drawable.cloud_snow_icon,
            85
    ),
    SNOW_SHOWERS_HEAVY(
            "Snow showers heavy",
            R.drawable.cloud_snow_icon,
            86
    ),
    THUNDERSTORM(
            "Thunderstorm slight or moderate",
            R.drawable.cloud_lightning_icon,
            95
    ),
    THUNDERSTORM_SLIGHT_HAIL(
            "Thunderstorm with slight hail",
            R.drawable.cloud_rain_lightning_icon,
            96
    ),
    THUNDERSTORM_HEAVY_HAIL(
            "Thunderstorm with heavy hail",
            R.drawable.cloud_rain_lightning_icon,
            99
    );

    public final String description;
    @DrawableRes
    public final int icon;
    public final int code;

    WeatherType(
            String description,
            int icon,
            int code
    ) {
        this.description = description;
        this.icon = icon;
        this.code = code;
    }

    public static WeatherType findWeatherTypeByCode(int code) {
        for (WeatherType weatherType : values()) {
            if (weatherType.code == code) {
                return weatherType;
            }
        }
        return null;
    }
}