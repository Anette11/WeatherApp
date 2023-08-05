package com.example.weatherapp.presentation.adapter;

enum ItemViewType {
    MAIN_INFO(0),
    HOURLY_INFO(1),
    TEXT(2);

    private final int type;

    ItemViewType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}