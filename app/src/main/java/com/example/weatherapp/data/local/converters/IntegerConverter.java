package com.example.weatherapp.data.local.converters;

import com.google.gson.reflect.TypeToken;

import java.util.List;

public class IntegerConverter extends Converter<Integer> {

    public IntegerConverter() {
        super(new TypeToken<List<Integer>>() {
        }.getType());
    }
}