package com.example.weatherapp.presentation.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.weatherapp.R;
import com.example.weatherapp.databinding.ActivityMainBinding;
import com.example.weatherapp.presentation.utils.Coordinates;
import com.example.weatherapp.presentation.utils.ToastProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private final int REQUEST_CODE_LOCATION_PERMISSIONS = 123;
    private MainActivityViewModel viewModel;

    @Inject
    ToastProvider toastProvider;

    @Override
    protected void onCreate(
            Bundle savedInstanceState
    ) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        viewModel.getErrorMessage().observe(this, errorMessage -> {
            if (errorMessage != null) toastProvider.showToast(errorMessage);
        });

        if (areLocationPermissionsGranted()) {
            getLocation();
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestLocationPermissions();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            @NonNull String[] permissions,
            @NonNull int[] grantResults
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_LOCATION_PERMISSIONS && grantResults.length > 0) {
            if (areLocationPermissionsGranted()) {
                getLocation();
            } else {
                viewModel.onErrorMessage(getString(R.string.location_permissions_are_not_granted));
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestLocationPermissions() {
        List<String> permissionsToRequestList = new ArrayList<>();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permissionsToRequestList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permissionsToRequestList.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        }
        if (!permissionsToRequestList.isEmpty()) {
            String[] permissionsToRequestArray = new String[permissionsToRequestList.size()];
            permissionsToRequestList.toArray(permissionsToRequestArray);
            requestPermissions(
                    permissionsToRequestArray,
                    REQUEST_CODE_LOCATION_PERMISSIONS
            );
        }
    }

    private boolean areLocationPermissionsGranted() {
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    private void getLocation() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        @SuppressLint("MissingPermission") Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (location != null) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            try {
                List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
                if (addresses != null) {
                    String cityName = addresses.get(0).getLocality();
                    viewModel.updateCoordinates(new Coordinates(latitude, longitude, cityName));
                }
            } catch (IOException e) {
                viewModel.onErrorMessage(getString(R.string.exception_in_retrieving_city_name));
            }
        } else {
            viewModel.onErrorMessage(getString(R.string.location_is_not_known));
        }
    }
}