package com.example.weatherapp.presentation.utils;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class LocationCoordinatesContainer {

    private final MutableLiveData<Coordinates> coordinates = new MutableLiveData<>(null);

    public void updateCoordinates(Coordinates newCoordinates) {
        coordinates.setValue(newCoordinates);
    }

    public LiveData<Coordinates> getCoordinates() {
        return coordinates;
    }
}