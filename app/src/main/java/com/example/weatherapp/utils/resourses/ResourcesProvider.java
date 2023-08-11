package com.example.weatherapp.utils.resourses;

import androidx.annotation.StringRes;

public interface ResourcesProvider {

    String getString(@StringRes int stringId);
}