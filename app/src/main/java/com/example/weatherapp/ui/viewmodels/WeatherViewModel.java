package com.example.weatherapp.ui.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.weatherapp.R;
import com.example.weatherapp.models.weather.WeatherType;
import com.example.weatherapp.models.weather.Hourly;
import com.example.weatherapp.use_cases.GetWeatherFromDbUseCase;
import com.example.weatherapp.use_cases.RefreshWeatherUseCase;
import com.example.weatherapp.ui.adapters.adapter_items.HourlyInfoEveryDayItem;
import com.example.weatherapp.ui.adapters.adapter_items.HourlyInfoItem;
import com.example.weatherapp.ui.adapters.adapter_items.MainInfoItem;
import com.example.weatherapp.ui.adapters.adapter_items.TextItem;
import com.example.weatherapp.ui.adapters.adapter_items.WeatherItem;
import com.example.weatherapp.models.coordinates.Coordinates;
import com.example.weatherapp.utils.formatters.DateFormatter;
import com.example.weatherapp.models.error_message.ErrorMessageContainer;
import com.example.weatherapp.models.coordinates.LocationCoordinatesContainer;
import com.example.weatherapp.utils.resourses.interfaces.ResourcesProvider;
import com.example.weatherapp.utils.formatters.StringFormatter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

@HiltViewModel
public class WeatherViewModel extends ViewModel {

    private final RefreshWeatherUseCase refreshWeatherUseCase;
    private final GetWeatherFromDbUseCase getWeatherUseCase;
    private final DateFormatter dateFormatter;
    private final StringFormatter stringFormatter;
    private final ResourcesProvider resourcesProvider;
    private final ErrorMessageContainer errorMessageContainer;
    private final LocationCoordinatesContainer locationCoordinatesContainer;
    private final MutableLiveData<List<WeatherItem>> weatherItems = new MutableLiveData<>(null);
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private boolean isWeatherInitiallyRequested = false;

    public boolean isWeatherInitiallyRequested() {
        return isWeatherInitiallyRequested;
    }

    public LiveData<List<WeatherItem>> getWeatherItems() {
        return weatherItems;
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public LiveData<Hourly> getWeatherFromDb() {
        return getWeatherUseCase.execute();
    }

    public void onErrorMessage(String errorMessage) {
        errorMessageContainer.onErrorMessage(errorMessage);
    }

    public ErrorMessageContainer getErrorMessageContainer() {
        return errorMessageContainer;
    }

    @Inject
    public WeatherViewModel(
            RefreshWeatherUseCase refreshWeatherUseCase,
            GetWeatherFromDbUseCase getWeatherFromDbUseCase,
            DateFormatter dateFormatter,
            StringFormatter stringFormatter,
            ResourcesProvider resourcesProvider,
            ErrorMessageContainer errorMessageHolder,
            LocationCoordinatesContainer locationCoordinatesContainer
    ) {
        this.refreshWeatherUseCase = refreshWeatherUseCase;
        this.getWeatherUseCase = getWeatherFromDbUseCase;
        this.dateFormatter = dateFormatter;
        this.stringFormatter = stringFormatter;
        this.resourcesProvider = resourcesProvider;
        this.errorMessageContainer = errorMessageHolder;
        this.locationCoordinatesContainer = locationCoordinatesContainer;
    }

    public void getWeather() {
        Coordinates coordinates = locationCoordinatesContainer.getCoordinates().getValue();
        if (coordinates == null) return;
        if (!isWeatherInitiallyRequested) isWeatherInitiallyRequested = true;
        compositeDisposable.clear();
        refreshWeatherUseCase
                .execute(coordinates.getLatitude(), coordinates.getLongitude())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable disposable) {
                        isLoading.postValue(true);
                        compositeDisposable.add(disposable);
                    }

                    @Override
                    public void onComplete() {
                        isLoading.postValue(false);
                    }

                    @Override
                    public void onError(@NonNull Throwable throwable) {
                        isLoading.postValue(false);
                        if (throwable instanceof IOException) {
                            errorMessageContainer.onErrorMessage(resourcesProvider.getString(R.string.no_internet_connection_error));
                        } else {
                            errorMessageContainer.onErrorMessage(resourcesProvider.getString(R.string.error_occurred));
                        }
                    }
                });
    }

    public void fillScreenItems(
            Hourly hourly
    ) {
        Completable.fromCallable(() -> {
                    createWeatherItems(hourly);
                    return hourly;
                })
                .subscribeOn(Schedulers.computation())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable disposable) {
                        compositeDisposable.add(disposable);
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        errorMessageContainer.onErrorMessage(resourcesProvider.getString(R.string.error_occurred));
                    }
                });
    }

    public void createWeatherItems(
            Hourly hourly
    ) {
        if (hourly == null) return;
        Coordinates coordinates = locationCoordinatesContainer.getCoordinates().getValue();
        if (coordinates == null) return;
        String cityName = coordinates.getCityName();
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
            onErrorMessage(resourcesProvider.getString(R.string.weather_items_can_not_be_created));
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
                                    coordinates.getLatitude(),
                                    coordinates.getLongitude()
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