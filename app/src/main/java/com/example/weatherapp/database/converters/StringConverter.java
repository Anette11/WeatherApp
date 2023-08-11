package com.example.weatherapp.database.converters;

import com.google.gson.reflect.TypeToken;

import java.util.List;

public class StringConverter extends Converter<String> {

    public StringConverter() {
        super(new TypeToken<List<String>>() {
        }.getType());
    }
}