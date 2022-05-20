package com.example.weatherapp.Events;

import com.example.weatherapp.Model.Weather;

public class WeatherEvents {

    private final Weather weather;

    public WeatherEvents(Weather weather) {
        this.weather=weather;
    }

    public Weather getWeather() {
        return weather;
    }
}
