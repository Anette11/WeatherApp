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
import com.example.weatherapp.presentation.adapter.items.MainInfoItem;
import com.example.weatherapp.presentation.adapter.items.WeatherItem;
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate;

import java.util.List;

public class MainInfoAdapterDelegate extends AbsListItemAdapterDelegate<MainInfoItem, WeatherItem, MainInfoAdapterDelegate.MainInfoViewHolder> {

    private final LayoutInflater inflater;

    public MainInfoAdapterDelegate(Activity activity) {
        inflater = activity.getLayoutInflater();
    }

    @Override
    protected boolean isForViewType(
            @NonNull WeatherItem item,
            @NonNull List<WeatherItem> items,
            int position
    ) {
        return item instanceof MainInfoItem;
    }

    @NonNull
    @Override
    protected MainInfoViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent
    ) {
        return new MainInfoViewHolder(inflater.inflate(R.layout.weather_main_info, parent, false));
    }

    @Override
    protected void onBindViewHolder(
            @NonNull MainInfoItem item,
            @NonNull MainInfoViewHolder holder,
            @NonNull List<Object> payloads
    ) {
        holder.textViewCityName.setText(item.getCityName());
        holder.textViewTemperature.setText(item.getTemperature() + " ℃");
        holder.textViewWindSpeed.setText(item.getWindSpeed() + " km/h");
        holder.textViewHumidity.setText(item.getHumidity() + " %");
        holder.textViewDescription.setText(item.getDescription());
        holder.imageView.setBackgroundResource(item.getImage());
    }

    static class MainInfoViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewCityName;
        public TextView textViewTemperature;
        public TextView textViewWindSpeed;
        public TextView textViewHumidity;
        public TextView textViewDescription;
        public ImageView imageView;

        public MainInfoViewHolder(View itemView) {
            super(itemView);
            textViewCityName = itemView.findViewById(R.id.text_view_city_name);
            textViewTemperature = itemView.findViewById(R.id.text_view_temperature);
            textViewWindSpeed = itemView.findViewById(R.id.text_view_wind_speed);
            textViewHumidity = itemView.findViewById(R.id.text_view_humidity);
            textViewDescription = itemView.findViewById(R.id.text_view_description);
            imageView = itemView.findViewById(R.id.image_view);
        }
    }
}