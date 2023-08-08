package com.example.weatherapp.presentation.utils;

import android.os.Looper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import javax.inject.Inject;

public class ErrorMessageHolder {

    @Inject
    public ErrorMessageHolder() {
    }

    private final MutableLiveData<String> errorMessage = new MutableLiveData<>(null);

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public void onErrorMessage(String newErrorMessage) {
        if (Thread.currentThread().equals(Looper.getMainLooper().getThread())) {
            errorMessage.setValue(newErrorMessage);
        } else {
            errorMessage.postValue(newErrorMessage);
        }
    }
}