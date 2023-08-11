package com.example.weatherapp.database.converters;

import com.google.gson.reflect.TypeToken;

import java.util.List;

public class IntegerConverter extends Converter<Integer> {

    public IntegerConverter() {
        super(new TypeToken<List<Integer>>() {
        }.getType());
    }
}