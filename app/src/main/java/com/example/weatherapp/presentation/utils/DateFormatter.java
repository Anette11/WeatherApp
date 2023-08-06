package com.example.weatherapp.presentation.utils;

import android.text.format.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import timber.log.Timber;

public class DateFormatter {
    private final SimpleDateFormat simpleDateFormatDefault = new SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm",
            Locale.getDefault()
    );

    public String convertDateFormat(
            String dateStr,
            String patternTo
    ) {
        SimpleDateFormat simpleDateFormatTo = new SimpleDateFormat(patternTo, Locale.getDefault());
        try {
            Date date = simpleDateFormatDefault.parse(dateStr);
            return simpleDateFormatTo.format(date);
        } catch (ParseException e) {
            Timber.d("Exception in parse date");
            e.printStackTrace();
            return null;
        }
    }

    public boolean checkIfDateIsToday(String dateStr) {
        try {
            Date dateToCheck = simpleDateFormatDefault.parse(dateStr);
            return DateUtils.isToday(dateToCheck.getTime());
        } catch (ParseException e) {
            Timber.d("Exception in parse date");
            e.printStackTrace();
            return false;
        }
    }

    public boolean isDateIsClosestToNow(String dateStr) {
        Date dateToday = new Date();
        try {
            Date dateToCheck = simpleDateFormatDefault.parse(dateStr);
            return checkIfDateIsToday(dateStr) && dateToday.getHours() == dateToCheck.getHours();
        } catch (ParseException e) {
            Timber.d("Exception in parse date");
            e.printStackTrace();
            return false;
        }
    }
}