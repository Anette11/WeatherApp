package com.example.weatherapp.ui.adapters.adapter_delegates;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.databinding.WeatherTextBinding;
import com.example.weatherapp.ui.adapters.adapter_items.TextItem;
import com.example.weatherapp.ui.adapters.adapter_items.WeatherItem;
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate;

import java.util.List;

public class TextAdapterDelegate extends AbsListItemAdapterDelegate<TextItem, WeatherItem, TextAdapterDelegate.TextViewHolder> {

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
        WeatherTextBinding binding = WeatherTextBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new TextViewHolder(binding);
    }

    @Override
    protected void onBindViewHolder(
            @NonNull TextItem item,
            @NonNull TextViewHolder holder,
            @NonNull List<Object> payloads
    ) {
        holder.binding.textView.setText(item.getText());
    }

    static class TextViewHolder extends RecyclerView.ViewHolder {

        private final WeatherTextBinding binding;

        public TextViewHolder(WeatherTextBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}