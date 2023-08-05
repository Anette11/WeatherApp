package com.example.weatherapp.presentation.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.R;
import com.example.weatherapp.presentation.adapter.items.TextItem;
import com.example.weatherapp.presentation.adapter.items.WeatherItem;
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate;

import java.util.List;

public class TextAdapterDelegate extends AbsListItemAdapterDelegate<TextItem, WeatherItem, TextAdapterDelegate.TextViewHolder> {

    private final LayoutInflater inflater;

    public TextAdapterDelegate(Activity activity) {
        inflater = activity.getLayoutInflater();
    }

    @Override
    protected boolean isForViewType(
            @NonNull WeatherItem item,
            @NonNull List<WeatherItem> items,
            int position
    ) {
        return item instanceof TextItem;
    }

    @NonNull
    @Override
    protected TextViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent
    ) {
        return new TextViewHolder(inflater.inflate(R.layout.weather_text, parent, false));
    }

    @Override
    protected void onBindViewHolder(
            @NonNull TextItem item,
            @NonNull TextViewHolder holder,
            @NonNull List<Object> payloads
    ) {
        holder.textView.setText(item.getText());
    }

    static class TextViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;

        public TextViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_view);
        }
    }
}
