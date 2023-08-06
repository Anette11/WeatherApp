package com.example.weatherapp.presentation.utils;

import android.text.format.DateUtils;

import com.example.weatherapp.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

import timber.log.Timber;

public class DateFormatter {

    @Inject
    ResourcesProvider resourcesProvider;

    private final SimpleDateFormat simpleDateFormatDefault = new SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm",
            Locale.getDefault()
    );

    private String convertDateFormat(
            String dateStr,
            String patternTo
    ) {
        SimpleDateFormat simpleDateFormatTo = new SimpleDateFormat(patternTo, Locale.getDefault());
        try {
            Date date = simpleDateFormatDefault.parse(dateStr);
            if (date == null) return null;
            return simpleDateFormatTo.format(date);
        } catch (ParseException e) {
            Timber.d(resourcesProvider.getString(R.string.exception_in_parse_date));
            return null;
        }
    }

    public String convertDateFormatInShortText(String dateStr) {
        return convertDateFormat(dateStr, "HH:mm");
    }

    public String convertDateFormatInLongText(String dateStr) {
        return convertDateFormat(dateStr, "dd MMMM yyyy");
    }

    public boolean checkIfDateIsToday(String dateStr) {
        try {
            Date dateToCheck = simpleDateFormatDefault.parse(dateStr);
            if (dateToCheck == null) return false;
            return DateUtils.isToday(dateToCheck.getTime());
        } catch (ParseException e) {
            Timber.d(resourcesProvider.getString(R.string.exception_in_parse_date));
            return false;
        }
    }

    public boolean isDateIsClosestToNow(String dateStr) {
        try {
            Date dateToCheck = simpleDateFormatDefault.parse(dateStr);
            Calendar calendarDateToCheck = Calendar.getInstance();
            Calendar calendarToday = Calendar.getInstance();
            if (dateToCheck == null) return false;
            calendarDateToCheck.setTime(dateToCheck);
            return checkIfDateIsToday(dateStr) &&
                    calendarToday.get(Calendar.HOUR_OF_DAY) == calendarDateToCheck.get(Calendar.HOUR_OF_DAY);
        } catch (ParseException e) {
            Timber.d(resourcesProvider.getString(R.string.exception_in_parse_date));
            return false;
        }
    }
}