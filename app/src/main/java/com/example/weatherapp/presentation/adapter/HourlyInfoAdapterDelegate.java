package com.example.weatherapp.presentation.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.R;
import com.example.weatherapp.presentation.adapter.items.HourlyInfoItem;
import com.example.weatherapp.presentation.adapter.items.WeatherItem;
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate;

import java.util.List;

public class HourlyInfoAdapterDelegate extends AbsListItemAdapterDelegate<HourlyInfoItem, WeatherItem, HourlyInfoAdapterDelegate.HourlyInfoViewHolder> {

    private final LayoutInflater inflater;

    public HourlyInfoAdapterDelegate(Activity activity) {
        inflater = activity.getLayoutInflater();
    }

    @Override
    protected boolean isForViewType(
            @NonNull WeatherItem item,
            @NonNull List<WeatherItem> items,
            int position
    ) {
        return item instanceof HourlyInfoItem;
    }

    @NonNull
    @Override
    protected HourlyInfoViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent
    ) {
        return new HourlyInfoAdapterDelegate.HourlyInfoViewHolder(inflater.inflate(R.layout.weather_hourly_info, parent, false));
    }

    @Override
    protected void onBindViewHolder(
            @NonNull HourlyInfoItem item,
            @NonNull HourlyInfoViewHolder holder,
            @NonNull List<Object> payloads
    ) {
        holder.textViewTime.setText(item.getTime());
        holder.textViewTemperature.setText(item.getTemperature() + " ℃");
        holder.textViewWindSpeed.setText(item.getWindSpeed() + " km/h");
        holder.textViewHumidity.setText(item.getHumidity() + " %");
        holder.textViewDescription.setText(item.getDescription());
        holder.imageView.setBackgroundResource(item.getImage());
    }

    static class HourlyInfoViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewTime;
        public TextView textViewTemperature;
        public TextView textViewWindSpeed;
        public TextView textViewHumidity;
        public TextView textViewDescription;
        public ImageView imageView;

        public HourlyInfoViewHolder(View itemView) {
            super(itemView);
            textViewTime = itemView.findViewById(R.id.text_view_time);
            textViewTemperature = itemView.findViewById(R.id.text_view_temperature);
            textViewWindSpeed = itemView.findViewById(R.id.text_view_wind_speed);
            textViewHumidity = itemView.findViewById(R.id.text_view_humidity);
            textViewDescription = itemView.findViewById(R.id.text_view_description);
            imageView = itemView.findViewById(R.id.image_view);
        }
    }
}