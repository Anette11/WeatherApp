package com.example.weatherapp.ui.toast;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.StringRes;

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

    public void showToast(@StringRes int message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}