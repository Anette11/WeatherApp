package com.example.weatherapp.utils;

import androidx.annotation.StringRes;

public interface ResourcesProvider {

    String getString(@StringRes int stringId);
}