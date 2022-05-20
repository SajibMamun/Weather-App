package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weatherapp.Events.WeatherEvents;
import com.example.weatherapp.Model.Currently;
import com.example.weatherapp.Model.Weather;
import com.example.weatherapp.Sevices.WeatherAPiServices;
import com.example.weatherapp.Sevices.WeatherApiServiceProvider;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.TemperatureTvid)
    TextView temptextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ButterKnife.bind(this);


        getweatherservices();


    }


    // Event Bus

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void findweatherevents(WeatherEvents weatherEvents) {
        // Do something

      Currently currently=  weatherEvents.getWeather().getCurrently();

      temptextView.setText(String.valueOf(currently.getTemperature()));

    }







    private void getweatherservices() {
        WeatherApiServiceProvider weatherApiServiceProvider = new WeatherApiServiceProvider();
        weatherApiServiceProvider.getWeather();
    }




}