package com.example.weatherapp.Sevices;

import com.example.weatherapp.Model.Weather;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WeatherAPiServices {

    @GET("forecast/7e87d17a004526d5f1ff090ae5eb689e/37.8267,-122.4233")
    Call<Weather> getWeatherData();
}
