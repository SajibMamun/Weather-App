package com.example.weatherapp.Sevices;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.weatherapp.Events.WeatherEvents;
import com.example.weatherapp.MainActivity;
import com.example.weatherapp.Model.Currently;
import com.example.weatherapp.Model.Weather;

import org.greenrobot.eventbus.EventBus;

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


    public void getWeather() {
        WeatherAPiServices weatherAPiServices = getretrofit().create(WeatherAPiServices.class);

        Call<Weather> callapi = weatherAPiServices.getWeatherData();

        callapi.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                Weather weather=response.body();

                Currently currently=weather.getCurrently();


                EventBus.getDefault().post(new WeatherEvents(weather));

            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {

            }
        });


    }
}
