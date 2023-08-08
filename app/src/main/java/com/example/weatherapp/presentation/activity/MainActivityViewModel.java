package com.example.weatherapp.presentation.activity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.weatherapp.presentation.utils.Coordinates;
import com.example.weatherapp.presentation.utils.ErrorMessageHolder;
import com.example.weatherapp.presentation.utils.LocationCoordinatesContainer;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class MainActivityViewModel extends ViewModel {

    private final LocationCoordinatesContainer locationCoordinatesContainer;

    private final ErrorMessageHolder errorMessageHolder;

    @Inject
    public MainActivityViewModel(
            LocationCoordinatesContainer locationCoordinatesContainer,
            ErrorMessageHolder errorMessageHolder
    ) {
        this.locationCoordinatesContainer = locationCoordinatesContainer;
        this.errorMessageHolder = errorMessageHolder;
    }

    public void updateCoordinates(Coordinates newCoordinates) {
        locationCoordinatesContainer.updateCoordinates(newCoordinates);
    }

    public LiveData<String> getErrorMessage() {
        return errorMessageHolder.getErrorMessage();
    }

    public void onErrorMessage(String newErrorMessage) {
        errorMessageHolder.onErrorMessage(newErrorMessage);
    }
}