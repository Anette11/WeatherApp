package com.example.weatherapp.utils;

import android.content.Context;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ApplicationContext;

public class ResourcesProviderImpl implements ResourcesProvider {

    private final Context context;

    @Inject
    public ResourcesProviderImpl(@ApplicationContext Context context) {
        this.context = context;
    }

    @Override
    public String getString(int stringId) {
        return context.getString(stringId);
    }
}