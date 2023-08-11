package com.example.weatherapp.database.converters;

import com.google.gson.reflect.TypeToken;

import java.util.List;

public class DoubleConverter extends Converter<Double> {

    public DoubleConverter() {
        super(new TypeToken<List<Double>>() {
        }.getType());
    }
}