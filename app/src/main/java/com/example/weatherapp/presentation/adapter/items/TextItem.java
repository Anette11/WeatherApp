package com.example.weatherapp.presentation.adapter.items;

public class TextItem implements WeatherItem {

    private final String text;

    public TextItem(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}