package com.example.weatherapp.data.local.converters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;
import java.util.List;

public abstract class Converter<T> {

    private final Gson gson;
    private final Type type;

    public Converter(Type type) {
        this.type = type;
        this.gson = new GsonBuilder().create();
    }

    public List<T> fromStringToList(String value) {
        return new Gson().fromJson(value, type);
    }

    public String fromListToString(List<T> t) {
        return gson.toJson(t);
    }
}