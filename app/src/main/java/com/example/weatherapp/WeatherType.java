package com.example.weatherapp;

import androidx.annotation.DrawableRes;

public enum WeatherType {

    CLEAR_SKY(
            "Clear sky",
            R.drawable.clear_day,
            0
    ),
    MAINLY_CLEAR(
            "Mainly clear",
            R.drawable.clear_day,
            1
    ),
    PARTLY_CLEAR(
            "Partly cloudy",
            R.drawable.cloudy_partly_day,
            2
    ),
    OVERCAST(
            "Overcast",
            R.drawable.cloudy,
            3
    ),
    FOG(
            "Fog",
            R.drawable.fog,
            45
    ),
    DEPOSITING_RIME_FOG(
            "Depositing rime fog",
            R.drawable.fog,
            48
    ),
    DRIZZLE_LIGHT(
            "Drizzle light",
            R.drawable.rain_small,
            51
    ),
    DRIZZLE_MODERATE(
            "Drizzle moderate",
            R.drawable.rain_small,
            53
    ),
    DRIZZLE_DENSE(
            "Drizzle dense",
            R.drawable.rain_small,
            55
    ),
    FREEZING_DRIZZLE_LIGHT(
            "Freezing drizzle light",
            R.drawable.snow_grains,
            56
    ),
    FREEZING_DRIZZLE_DENSE(
            "Freezing drizzle dense",
            R.drawable.snow_grains,
            57
    ),
    RAIN_SLIGHT(
            "Rain slight",
            R.drawable.rain_heavy,
            61
    ),
    RAIN_MODERATE(
            "Rain moderate",
            R.drawable.rain_heavy,
            63
    ),
    RAIN_HEAVY(
            "Rain heavy",
            R.drawable.rain_heavy,
            65
    ),
    FREEZING_RAIN_LIGHT(
            "Freezing rain light",
            R.drawable.snow_grains,
            66
    ),
    FREEZING_RAIN_HEAVY(
            "Freezing rain heavy",
            R.drawable.snow_grains,
            67
    ),
    SNOW_FALL_SLIGHT(
            "Snow fall slight",
            R.drawable.snow,
            71
    ),
    SNOW_FALL_MODERATE(
            "Snow fall moderate",
            R.drawable.snow,
            73
    ),
    SNOW_FALL_HEAVY(
            "Snow fall heavy",
            R.drawable.snow,
            75
    ),
    SNOW_GRAINS(
            "Snow grains",
            R.drawable.snow_grains,
            77
    ),
    RAIN_SHOWERS_SLIGHT(
            "Rain showers slight",
            R.drawable.rain_shower,
            80
    ),
    RAIN_SHOWERS_MODERATE(
            "Rain showers moderate",
            R.drawable.rain_shower,
            81
    ),
    RAIN_SHOWERS_VIOLENT(
            "Rain showers violent",
            R.drawable.rain_shower,
            82
    ),
    SNOW_SHOWERS_SLIGHT(
            "Snow showers slight",
            R.drawable.snow,
            85
    ),
    SNOW_SHOWERS_HEAVY(
            "Snow showers heavy",
            R.drawable.snow,
            86
    ),
    THUNDERSTORM(
            "Thunderstorm slight or moderate",
            R.drawable.thunderstorm,
            95
    ),
    THUNDERSTORM_SLIGHT_HAIL(
            "Thunderstorm with slight hail",
            R.drawable.thunderstorm_rain,
            96
    ),
    THUNDERSTORM_HEAVY_HAIL(
            "Thunderstorm with heavy hail",
            R.drawable.thunderstorm_rain,
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
}