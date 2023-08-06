package com.example.weatherapp.presentation.utils;

import android.content.Context;
import android.widget.Toast;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ApplicationContext;

public class ToastProvider {

    private final Context context;

    @Inject
    public ToastProvider(@ApplicationContext Context context) {
        this.context = context;
    }

    public void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}