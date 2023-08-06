package com.example.weatherapp.presentation.frgment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.weatherapp.WeatherType;
import com.example.weatherapp.domain.data.GetWeatherResponse;
import com.example.weatherapp.domain.data.Hourly;
import com.example.weatherapp.domain.use_case.GetWeatherUseCase;
import com.example.weatherapp.presentation.adapter.items.HourlyInfoEveryDayItem;
import com.example.weatherapp.presentation.adapter.items.HourlyInfoItem;
import com.example.weatherapp.presentation.adapter.items.MainInfoItem;
import com.example.weatherapp.presentation.adapter.items.TextItem;
import com.example.weatherapp.presentation.adapter.items.WeatherItem;
import com.example.weatherapp.presentation.utils.Coordinates;
import com.example.weatherapp.presentation.utils.DateFormatter;
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
    private final DateFormatter dateFormatter;
    private final LocationCoordinatesContainer locationCoordinatesContainer;
    private final MutableLiveData<List<WeatherItem>> weatherItems = new MutableLiveData<>(null);
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);

    public LiveData<List<WeatherItem>> getWeatherItems() {
        return weatherItems;
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    @Inject
    public WeatherViewModel(
            GetWeatherUseCase getWeatherUseCase,
            DateFormatter dateFormatter,
            LocationCoordinatesContainer locationCoordinatesContainer
    ) {
        this.getWeatherUseCase = getWeatherUseCase;
        this.dateFormatter = dateFormatter;
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
                        isLoading.postValue(true);
                    }

                    @Override
                    public void onSuccess(@NonNull GetWeatherResponse getWeatherResponse) {
                        createWeatherItems(getWeatherResponse);
                        isLoading.postValue(false);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        isLoading.postValue(false);
                    }
                });
    }

    private void createWeatherItems(
            GetWeatherResponse getWeatherResponse
    ) {
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
        int numberOfValuesPerDay = 24;
        if (time.size() % numberOfValuesPerDay != 0 ||
                time.size() != weatherCode.size() ||
                time.size() != temperature.size() ||
                time.size() != windSpeed.size() ||
                time.size() != humidity.size()
        ) {
            Timber.d("WeatherItems can not be created");
            return;
        }
        List<WeatherItem> newWeatherItems = new ArrayList<>();
        List<WeatherItem> itemList = new ArrayList<>();
        int counter = 0;
        for (int i = 0; i < time.size(); i++) {
            counter++;
            if (counter == 1) {
                boolean isDateToday = dateFormatter.checkIfDateIsToday(time.get(i));
                String dateStr = time.get(i);
                if (isDateToday) {
                    dateStr = "Today";
                } else {
                    dateStr = dateFormatter.convertDateFormat(dateStr, "dd MMMM yyyy");
                }
                newWeatherItems.add(new TextItem(dateStr));
            }
            WeatherType weatherType = WeatherType.findWeatherTypeByCode(weatherCode.get(i));
            if (weatherType != null) {
                itemList.add(
                        new HourlyInfoItem(
                                dateFormatter.convertDateFormat(
                                        time.get(i),
                                        "HH:mm"
                                ),
                                weatherType.icon,
                                temperature.get(i),
                                windSpeed.get(i),
                                humidity.get(i),
                                weatherType.description
                        ));
                boolean isDateIsClosestToNow = dateFormatter.isDateIsClosestToNow(time.get(i));
                if (isDateIsClosestToNow) {
                    List<WeatherItem> mainInfoNow = new ArrayList<>();
                    mainInfoNow.add(new TextItem("Now"));
                    mainInfoNow.add(
                            new MainInfoItem(
                                    cityName,
                                    weatherType.icon,
                                    temperature.get(i),
                                    windSpeed.get(i),
                                    humidity.get(i),
                                    weatherType.description,
                                    dateFormatter.convertDateFormat(
                                            time.get(i),
                                            "HH:mm"
                                    )
                            )
                    );
                    newWeatherItems.addAll(0, mainInfoNow);
                }
            }
            if (counter == numberOfValuesPerDay) {
                newWeatherItems.add(new HourlyInfoEveryDayItem(new ArrayList<>(itemList)));
                counter = 0;
                itemList.clear();
            }
        }
        weatherItems.postValue(newWeatherItems);
    }
}