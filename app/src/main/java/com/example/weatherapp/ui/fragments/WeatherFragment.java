package com.example.weatherapp.ui.fragments;

import android.content.Intent;
import android.net.Uri;
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
import com.example.weatherapp.ui.adapters.WeatherAdapter;
import com.example.weatherapp.ui.adapters.adapter_items.MainInfoItem;
import com.example.weatherapp.ui.viewmodels.WeatherViewModel;
import com.example.weatherapp.utils.LocationCoordinatesContainer;
import com.example.weatherapp.utils.ToastProvider;

import java.util.Locale;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

@AndroidEntryPoint
public class WeatherFragment extends Fragment {

    private FragmentWeatherBinding binding;
    private WeatherAdapter weatherAdapter;
    private WeatherViewModel viewModel;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    ToastProvider toastProvider;

    @Inject
    LocationCoordinatesContainer locationCoordinatesContainer;

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
        viewModel = new ViewModelProvider(requireActivity()).get(WeatherViewModel.class);
        observeErrorMessages();
        observeCoordinates();
        observeWeatherItems();
        observeLoadingStatus();
        observeWeatherFromDb();
        setOnRefreshListener();
        setRecyclerView();
    }

    private void observeErrorMessages() {
        Disposable disposable = viewModel.getErrorMessageContainer()
                .getPublishSubject()
                .subscribe(
                        errorMessage -> toastProvider.showToast(errorMessage),
                        error -> viewModel.onErrorMessage(getString(R.string.error_occurred))
                );
        compositeDisposable.add(disposable);
    }

    private void observeCoordinates() {
        locationCoordinatesContainer.getCoordinates().observe(
                getViewLifecycleOwner(),
                coordinates -> {
                    if (coordinates != null && !viewModel.isWeatherInitiallyRequested()) {
                        viewModel.getWeather();
                    }
                });
    }

    private void observeWeatherItems() {
        viewModel.getWeatherItems().observe(
                getViewLifecycleOwner(),
                weatherItems -> {
                    if (weatherItems != null) weatherAdapter.updateWeatherItems(weatherItems);
                });
    }

    private void observeLoadingStatus() {
        viewModel.getIsLoading().observe(
                getViewLifecycleOwner(),
                isLoading -> binding.progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE)
        );
    }

    private void observeWeatherFromDb() {
        viewModel.getWeatherFromDb().observe(
                getViewLifecycleOwner(),
                viewModel::fillScreenItems
        );
    }

    private void setOnRefreshListener() {
        binding.swipeRefreshLayout.setOnRefreshListener(() -> {
            viewModel.getWeather();
            binding.swipeRefreshLayout.setRefreshing(false);
        });
    }

    private void setRecyclerView() {
        weatherAdapter = new WeatherAdapter(weatherItem -> {
            if (weatherItem instanceof MainInfoItem) {
                onLocationClick(
                        ((MainInfoItem) weatherItem).getLatitude(),
                        ((MainInfoItem) weatherItem).getLongitude()
                );
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireActivity());
        binding.recyclerView.setLayoutManager(linearLayoutManager);
        binding.recyclerView.setAdapter(weatherAdapter);
    }

    private void onLocationClick(
            double latitude,
            double longitude
    ) {
        try {
            String coordinates = String.format(Locale.getDefault(), "geo:%f,%f", latitude, longitude);
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(coordinates));
            startActivity(intent);
        } catch (Exception e) {
            viewModel.onErrorMessage(getString(R.string.exception_in_show_location_in_map));
        }
    }

    @Override
    public void onDestroyView() {
        binding = null;
        compositeDisposable.dispose();
        super.onDestroyView();
    }
}