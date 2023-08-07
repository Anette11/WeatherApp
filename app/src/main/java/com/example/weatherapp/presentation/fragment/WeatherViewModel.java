package com.example.weatherapp.presentation.fragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.weatherapp.R;
import com.example.weatherapp.WeatherType;
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
import com.example.weatherapp.presentation.utils.ResourcesProvider;
import com.example.weatherapp.presentation.utils.StringFormatter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import timber.log.Timber;

@HiltViewModel
public class WeatherViewModel extends ViewModel {

    private final GetWeatherUseCase getWeatherUseCase;
    private final DateFormatter dateFormatter;
    private final StringFormatter stringFormatter;
    private final ResourcesProvider resourcesProvider;
    private final LocationCoordinatesContainer locationCoordinatesContainer;
    private final MutableLiveData<List<WeatherItem>> weatherItems = new MutableLiveData<>(null);
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);
    private final MutableLiveData<Boolean> isWeatherInfoFetched = new MutableLiveData<>(false);
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

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
            StringFormatter stringFormatter,
            ResourcesProvider resourcesProvider,
            LocationCoordinatesContainer locationCoordinatesContainer
    ) {
        this.getWeatherUseCase = getWeatherUseCase;
        this.dateFormatter = dateFormatter;
        this.stringFormatter = stringFormatter;
        this.resourcesProvider = resourcesProvider;
        this.locationCoordinatesContainer = locationCoordinatesContainer;
    }

    public LocationCoordinatesContainer getLocationCoordinatesContainer() {
        return locationCoordinatesContainer;
    }

    public void getWeather(
            double latitude,
            double longitude
    ) {
        if (isWeatherInfoFetched.getValue() == Boolean.FALSE) {
            getWeatherUseCase.execute(latitude, longitude, dateFormatter.getTimezone())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new SingleObserver<Hourly>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {
                            isLoading.postValue(true);
                            compositeDisposable.add(d);
                        }

                        @Override
                        public void onSuccess(@NonNull Hourly hourly) {
                            createWeatherItems(hourly);
                            isWeatherInfoFetched.postValue(true);
                            isLoading.postValue(false);
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            isLoading.postValue(false);
                        }
                    });
        }
    }

    private void createWeatherItems(
            Hourly hourly
    ) {
        Coordinates coordinates = locationCoordinatesContainer.getCoordinates().getValue();
        String cityName = coordinates != null ? coordinates.getCityName() : resourcesProvider.getString(R.string.not_applicable);
        List<String> time = hourly.getTime();
        List<Integer> weatherCode = hourly.getWeatherCode();
        List<Double> temperature = hourly.getTemperature();
        List<Double> windSpeed = hourly.getWindSpeed();
        List<Integer> humidity = hourly.getHumidity();
        int numberOfValuesPerDay = 24;
        if (time.size() % numberOfValuesPerDay != 0 ||
                time.size() != weatherCode.size() ||
                time.size() != temperature.size() ||
                time.size() != windSpeed.size() ||
                time.size() != humidity.size()
        ) {
            Timber.d(resourcesProvider.getString(R.string.weather_items_can_not_be_created));
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
                dateStr = isDateToday ? resourcesProvider.getString(R.string.today) : dateFormatter.convertDateFormatInLongText(dateStr);
                newWeatherItems.add(new TextItem(dateStr));
            }
            WeatherType weatherType = WeatherType.findWeatherTypeByCode(weatherCode.get(i));
            if (weatherType != null) {
                itemList.add(
                        new HourlyInfoItem(
                                dateFormatter.convertDateFormatInShortText(time.get(i)),
                                weatherType.icon,
                                stringFormatter.formatToTemperature(temperature.get(i)),
                                stringFormatter.formatToWindSpeed(windSpeed.get(i)),
                                stringFormatter.formatToHumidity(humidity.get(i)),
                                weatherType.description
                        ));
                boolean isDateIsClosestToNow = dateFormatter.isDateIsClosestToNow(time.get(i));
                if (isDateIsClosestToNow) {
                    List<WeatherItem> mainInfoNow = new ArrayList<>();
                    mainInfoNow.add(new TextItem(resourcesProvider.getString(R.string.now)));
                    mainInfoNow.add(
                            new MainInfoItem(
                                    cityName,
                                    weatherType.icon,
                                    stringFormatter.formatToTemperature(temperature.get(i)),
                                    stringFormatter.formatToWindSpeed(windSpeed.get(i)),
                                    stringFormatter.formatToHumidity(humidity.get(i)),
                                    weatherType.description,
                                    dateFormatter.convertDateFormatInShortText(time.get(i))
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

    @Override
    protected void onCleared() {
        compositeDisposable.dispose();
        super.onCleared();
    }
}