package com.example.weatherapp.Sevices;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.weatherapp.MainActivity;
import com.example.weatherapp.Model.Currently;
import com.example.weatherapp.Model.Weather;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherApiServiceProvider {
    private Retrofit retrofit;
    private Context context;

    Retrofit getretrofit() {
        if (this.retrofit == null) {
            this.retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.darksky.net/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();


        }


        return this.retrofit;
    }


    public void getWeather(Callback callback) {
        WeatherAPiServices weatherAPiServices = getretrofit().create(WeatherAPiServices.class);

        Call<Weather> callapi = weatherAPiServices.getWeatherData();

        callapi.enqueue(callback);


    }
}
