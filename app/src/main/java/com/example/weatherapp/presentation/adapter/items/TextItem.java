package com.example.weatherapp.presentation.adapter.items;

public class TextItem extends WeatherItem {

    private final String text;

    public TextItem(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public int compareTo(WeatherItem weatherItem) {
        return (((TextItem) weatherItem).text.compareTo(this.text));
    }
}