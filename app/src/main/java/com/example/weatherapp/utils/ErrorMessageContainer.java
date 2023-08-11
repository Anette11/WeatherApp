package com.example.weatherapp.utils;

import io.reactivex.rxjava3.subjects.PublishSubject;

public class ErrorMessageContainer {

    private final PublishSubject<String> publishSubject = PublishSubject.create();

    public PublishSubject<String> getPublishSubject() {
        return publishSubject;
    }

    public void onErrorMessage(String errorMessage) {
        publishSubject.onNext(errorMessage);
    }
}