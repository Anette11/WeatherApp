package com.example.weatherapp.presentation.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.weatherapp.R;
import com.example.weatherapp.databinding.ActivityMainBinding;
import com.example.weatherapp.presentation.dialog.AlertDialogCreator;
import com.example.weatherapp.presentation.utils.Coordinates;
import com.example.weatherapp.presentation.utils.LocationCoordinatesContainer;
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

    @Inject
    ToastProvider toastProvider;

    @Inject
    LocationCoordinatesContainer locationCoordinatesContainer;

    @Override
    protected void onCreate(
            Bundle savedInstanceState
    ) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if (areLocationPermissionsGranted()) {
            getLocation();
        } else {
            showDialog();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (areLocationPermissionsGranted() &&
                locationCoordinatesContainer.getCoordinates().getValue() == null
        ) {
            getLocation();
        }
    }

    private void showDialog() {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) return;
        if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION) ||
                shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION)) {
            new AlertDialogCreator(
                    this,
                    getString(R.string.dialog_location_permissions_title),
                    getString(R.string.dialog_location_permissions_message_open_settings),
                    getString(R.string.dialog_location_permissions_positive_button_text_open_settings),
                    getString(R.string.dialog_location_permissions_negative_button_text),
                    this::openAppSettingsIntent,
                    this::finish
            ).createAlertDialog().show();
        } else {
            new AlertDialogCreator(
                    this,
                    getString(R.string.dialog_location_permissions_title),
                    getString(R.string.dialog_location_permissions_message_continue),
                    getString(R.string.dialog_location_permissions_positive_button_text_continue),
                    getString(R.string.dialog_location_permissions_negative_button_text),
                    this::requestLocationPermissions,
                    this::finish
            ).createAlertDialog().show();
        }
    }

    private void openAppSettingsIntent() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivity(intent);
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
                toastProvider.showToast(R.string.location_permissions_are_not_granted);
                finish();
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
        if (location == null) {
            toastProvider.showToast(R.string.location_is_not_known);
            return;
        }
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(
                    location.getLatitude(),
                    location.getLongitude(),
                    1
            );
            if (addresses == null || addresses.isEmpty()) return;
            locationCoordinatesContainer.updateCoordinates(
                    new Coordinates(
                            location.getLatitude(),
                            location.getLongitude(),
                            addresses.get(0).getLocality()
                    ));
        } catch (IOException e) {
            toastProvider.showToast(R.string.exception_in_retrieving_city_name);
        }
    }
}