package com.example.weatherapp.presentation.activity;

import androidx.lifecycle.ViewModel;

import com.example.weatherapp.utils.Coordinates;
import com.example.weatherapp.utils.LocationCoordinatesContainer;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class MainActivityViewModel extends ViewModel {

    private final LocationCoordinatesContainer locationCoordinatesContainer;

    @Inject
    public MainActivityViewModel(LocationCoordinatesContainer locationCoordinatesContainer) {
        this.locationCoordinatesContainer = locationCoordinatesContainer;
    }

    public void updateCoordinates(Coordinates newCoordinates) {
        locationCoordinatesContainer.updateCoordinates(newCoordinates);
    }
}
