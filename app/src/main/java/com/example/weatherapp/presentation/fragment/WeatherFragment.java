package com.example.weatherapp.presentation.fragment;

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
import com.example.weatherapp.presentation.adapter.WeatherAdapter;
import com.example.weatherapp.presentation.adapter.items.MainInfoItem;
import com.example.weatherapp.presentation.utils.LocationCoordinatesContainer;
import com.example.weatherapp.presentation.utils.ToastProvider;

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

        Disposable disposableErrorMessage = viewModel
                .getErrorMessageContainer()
                .getPublishSubject()
                .subscribe(
                        errorMessage -> toastProvider.showToast(errorMessage),
                        error -> toastProvider.showToast(getString(R.string.error_occurred))
                );
        compositeDisposable.add(disposableErrorMessage);

        weatherAdapter = new WeatherAdapter(weatherItem -> {
            if (weatherItem instanceof MainInfoItem) {
                onLocationClick(
                        ((MainInfoItem) weatherItem).getLatitude(),
                        ((MainInfoItem) weatherItem).getLongitude()
                );
            }
        });

        setRecyclerView();

        locationCoordinatesContainer.getCoordinates().observe(getViewLifecycleOwner(), coordinates -> {
            if (coordinates != null) viewModel.getWeather();
        });

        viewModel.getWeatherItems().observe(
                getViewLifecycleOwner(),
                weatherItems -> weatherAdapter.updateWeatherItems(weatherItems)
        );

        viewModel.getIsLoading().observe(
                getViewLifecycleOwner(),
                isLoading -> binding.progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE)
        );

        viewModel.getWeatherFromDb().observe(
                getViewLifecycleOwner(),
                viewModel::createWeatherItems
        );

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