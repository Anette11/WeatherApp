package com.example.weatherapp.utils.resourses.interfaces;

import androidx.annotation.StringRes;

public interface ResourcesProvider {

    String getString(@StringRes int stringId);
}