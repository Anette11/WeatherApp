package com.example.weatherapp.utils.resourses;

import android.content.Context;

import com.example.weatherapp.utils.resourses.interfaces.ResourcesProvider;

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