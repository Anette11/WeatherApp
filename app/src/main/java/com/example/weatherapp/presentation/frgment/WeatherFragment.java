package com.example.weatherapp.presentation.frgment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.weatherapp.R;
import com.example.weatherapp.databinding.FragmentWeatherBinding;
import com.example.weatherapp.presentation.adapter.WeatherRecyclerViewAdapter;
import com.example.weatherapp.presentation.adapter.items.HourlyInfoItem;
import com.example.weatherapp.presentation.adapter.items.MainInfoItem;
import com.example.weatherapp.presentation.adapter.items.WeatherItem;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class WeatherFragment extends Fragment {

    private FragmentWeatherBinding binding;

    @Inject
    WeatherRecyclerViewAdapter recyclerViewAdapter;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        binding = FragmentWeatherBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(
            @NonNull View view,
            @Nullable Bundle savedInstanceState
    ) {
        super.onViewCreated(view, savedInstanceState);
        setRecyclerView();

        WeatherViewModel viewModel = new ViewModelProvider(requireActivity()).get(WeatherViewModel.class);

        viewModel.getLocationCoordinatesContainer()
                .getCoordinates()
                .observe(getViewLifecycleOwner(), coordinates -> {
                    if (coordinates != null) {
                        viewModel.getWeather(coordinates.getLatitude(), coordinates.getLongitude());
                    }
                });
    }

    private void setRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireActivity());
        binding.recyclerView.setLayoutManager(linearLayoutManager);
        binding.recyclerView.setAdapter(recyclerViewAdapter);

        List<WeatherItem> newWeatherItems = new ArrayList<>();
        newWeatherItems.add(
                new MainInfoItem(
                        "City name",
                        R.drawable.day_sunny_icon,
                        30.0,
                        14.5,
                        80,
                        "Clear sky"
                ));
        newWeatherItems.add(new HourlyInfoItem("12:00"));
        newWeatherItems.add(new HourlyInfoItem("13:00"));
        newWeatherItems.add(new HourlyInfoItem("14:00"));
        newWeatherItems.add(new HourlyInfoItem("15:00"));
        newWeatherItems.add(new HourlyInfoItem("16:00"));
        recyclerViewAdapter.updateWeatherItems(newWeatherItems);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}