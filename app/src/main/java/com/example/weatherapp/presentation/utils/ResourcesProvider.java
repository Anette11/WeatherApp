package com.example.weatherapp.presentation.utils;

import androidx.annotation.StringRes;

public interface ResourcesProvider {

    String getString(@StringRes int stringId);
}