package com.example.weatherapp.presentation.dialog;

import android.content.Context;

import androidx.appcompat.app.AlertDialog;

public class AlertDialogCreator {

    private final Context context;
    private final String title;
    private final String message;
    private final String positiveButton;
    private final String negativeButton;
    private final boolean isCancelable;
    private final OnButtonClick onPositiveButtonClick;
    private final OnButtonClick onNegativeButtonClick;

    public AlertDialogCreator(
            Context context,
            String title,
            String message,
            String positiveButton,
            String negativeButton,
            boolean isCancelable,
            OnButtonClick onPositiveButtonClick,
            OnButtonClick onNegativeButtonClick
    ) {
        this.context = context;
        this.title = title;
        this.message = message;
        this.positiveButton = positiveButton;
        this.negativeButton = negativeButton;
        this.isCancelable = isCancelable;
        this.onPositiveButtonClick = onPositiveButtonClick;
        this.onNegativeButtonClick = onNegativeButtonClick;
    }

    public AlertDialog createAlertDialog() {
        return new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(
                        positiveButton,
                        (dialog, which) -> {
                            dialog.dismiss();
                            onPositiveButtonClick.onClick();
                        })
                .setNegativeButton(
                        negativeButton,
                        (dialog, which) -> {
                            dialog.dismiss();
                            onNegativeButtonClick.onClick();
                        })
                .setCancelable(isCancelable)
                .create();
    }
}