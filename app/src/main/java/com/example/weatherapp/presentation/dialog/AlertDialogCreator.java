package com.example.weatherapp.presentation.dialog;

import android.content.Context;

import androidx.appcompat.app.AlertDialog;

public class AlertDialogCreator {

    private final Context context;
    private final String title;
    private final String message;
    private final String positiveButton;
    private final String negativeButton;
    private final OnButtonClick onPositiveButtonClick;
    private final OnButtonClick onNegativeButtonClick;

    public AlertDialogCreator(
            Context context,
            String title,
            String message,
            String positiveButton,
            String negativeButton,
            OnButtonClick onPositiveButtonClick,
            OnButtonClick onNegativeButtonClick
    ) {
        this.context = context;
        this.title = title;
        this.message = message;
        this.positiveButton = positiveButton;
        this.negativeButton = negativeButton;
        this.onPositiveButtonClick = onPositiveButtonClick;
        this.onNegativeButtonClick = onNegativeButtonClick;
    }

    public AlertDialog createAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(positiveButton, (dialog, which) -> onPositiveButtonClick.onClick());
        builder.setNegativeButton(negativeButton, (dialog, which) -> onNegativeButtonClick.onClick());
        builder.setCancelable(false);
        return builder.create();
    }
}