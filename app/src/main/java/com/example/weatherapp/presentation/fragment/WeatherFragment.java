package com.example.weatherapp.presentation.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.weatherapp.databinding.FragmentWeatherBinding;
import com.example.weatherapp.presentation.adapter.WeatherAdapter;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class WeatherFragment extends Fragment {

    private FragmentWeatherBinding binding;

    @Inject
    WeatherAdapter weatherAdapter;

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
                .observe(getViewLifecycleOwner(), coordinates -> viewModel.getWeather());

        viewModel.getWeatherItems().observe(getViewLifecycleOwner(), weatherItems -> {
            if (weatherItems != null) weatherAdapter.updateWeatherItems(weatherItems);
        });

        viewModel.getIsLoading().observe(
                getViewLifecycleOwner(),
                isLoading -> binding.progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE)
        );

        viewModel.getWeatherFromDb().observe(getViewLifecycleOwner(), hourly -> {
            if (hourly != null) viewModel.createWeatherItems(hourly);
        });

        binding.swipeRefreshLayout.setOnRefreshListener(() -> {
            viewModel.getWeather();
            binding.swipeRefreshLayout.setRefreshing(false);
        });
    }

    private void setRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireActivity());
        binding.recyclerView.setLayoutManager(linearLayoutManager);
        binding.recyclerView.setAdapter(weatherAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}