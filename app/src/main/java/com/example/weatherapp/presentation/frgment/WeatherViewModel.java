package com.example.weatherapp.presentation.frgment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.weatherapp.WeatherType;
import com.example.weatherapp.domain.data.GetWeatherResponse;
import com.example.weatherapp.domain.data.Hourly;
import com.example.weatherapp.domain.use_case.GetWeatherUseCase;
import com.example.weatherapp.presentation.adapter.items.HourlyInfoItem;
import com.example.weatherapp.presentation.adapter.items.MainInfoItem;
import com.example.weatherapp.presentation.adapter.items.TextItem;
import com.example.weatherapp.presentation.adapter.items.WeatherItem;
import com.example.weatherapp.presentation.utils.Coordinates;
import com.example.weatherapp.presentation.utils.LocationCoordinatesContainer;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import timber.log.Timber;

@HiltViewModel
public class WeatherViewModel extends ViewModel {

    private final GetWeatherUseCase getWeatherUseCase;
    private final LocationCoordinatesContainer locationCoordinatesContainer;
    private final MutableLiveData<List<WeatherItem>> weatherItems = new MutableLiveData<>(null);

    public LiveData<List<WeatherItem>> getWeatherItems() {
        return weatherItems;
    }

    @Inject
    public WeatherViewModel(
            GetWeatherUseCase getWeatherUseCase,
            LocationCoordinatesContainer locationCoordinatesContainer
    ) {
        this.getWeatherUseCase = getWeatherUseCase;
        this.locationCoordinatesContainer = locationCoordinatesContainer;
    }

    public LocationCoordinatesContainer getLocationCoordinatesContainer() {
        return locationCoordinatesContainer;
    }

    public void getWeather(
            double latitude,
            double longitude
    ) {
        getWeatherUseCase.execute(latitude, longitude)
                .subscribeOn(Schedulers.io())
                .subscribe(new SingleObserver<GetWeatherResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Timber.d("onSubscribe()");
                    }

                    @Override
                    public void onSuccess(@NonNull GetWeatherResponse getWeatherResponse) {
                        createWeatherItems(getWeatherResponse);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Timber.d("onError()");
                    }
                });
    }

    private void createWeatherItems(GetWeatherResponse getWeatherResponse) {
        Coordinates coordinates = locationCoordinatesContainer.getCoordinates().getValue();
        String cityName;
        if (coordinates != null) {
            cityName = coordinates.getCityName();
        } else {
            cityName = "N/A";
        }
        Hourly hourly = getWeatherResponse.getHourly();
        List<String> time = hourly.getTime();
        List<Integer> weatherCode = hourly.getWeatherCode();
        List<Double> temperature = hourly.getTemperature2m();
        List<Double> windSpeed = hourly.getWindSpeed10m();
        List<Integer> humidity = hourly.getRelativeHumidity2m();
        if (time.size() == 0 ||
                time.size() != weatherCode.size() ||
                time.size() != temperature.size() ||
                time.size() != windSpeed.size() ||
                time.size() != humidity.size()
        ) {
            Timber.d("WeatherItems can not be created");
            return;
        }
        List<WeatherItem> newWeatherItems = new ArrayList<>();
        for (int i = 0; i < time.size(); i++) {
            WeatherType weatherType = WeatherType.findWeatherTypeByCode(weatherCode.get(i));
            if (weatherType != null) {
                if (i == 0) {
                    newWeatherItems.add(new TextItem("Now"));
                    newWeatherItems.add(
                            new MainInfoItem(
                                    cityName,
                                    weatherType.icon,
                                    temperature.get(i),
                                    windSpeed.get(i),
                                    humidity.get(i),
                                    weatherType.description
                            ));
                    newWeatherItems.add(new TextItem("Today"));
                }
                newWeatherItems.add(
                        new HourlyInfoItem(
                                time.get(i),
                                weatherType.icon,
                                temperature.get(i),
                                windSpeed.get(i),
                                humidity.get(i),
                                weatherType.description
                        ));
            }
        }
        weatherItems.postValue(newWeatherItems);
    }
}