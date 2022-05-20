package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weatherapp.Events.ErrorEvents;
import com.example.weatherapp.Events.WeatherEvents;
import com.example.weatherapp.Model.Currently;
import com.example.weatherapp.Model.Weather;
import com.example.weatherapp.Sevices.WeatherAPiServices;
import com.example.weatherapp.Sevices.WeatherApiServiceProvider;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

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
    @BindView(R.id.textsummaryid)
    TextView summaryid;
    @BindView(R.id.weatherimageid)
    ImageView weatherimageid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

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


    //this is for get data
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onWeatherEvent(WeatherEvents weatherEvents) {
        // Do something

        Currently currently = weatherEvents.getWeather().getCurrently();

        temptextView.setText(String.valueOf(currently.getTemperature()));
        summaryid.setText(currently.getSummary());


        Map<String, Integer> iconMap = new HashMap<>();
        iconMap.put("clear-day", R.drawable.clearday);
        iconMap.put("clear-night", R.drawable.clearnight);
        iconMap.put("rain", R.drawable.rain);
        iconMap.put("snow", R.drawable.snow);
        iconMap.put("sleet", R.drawable.sleet);
        iconMap.put("wind", R.drawable.wind);
        iconMap.put("fog", R.drawable.fog);
        iconMap.put("cloudy", R.drawable.cloudy);
        iconMap.put("partly-cloudy-day", R.drawable.partlycloudyday);
        iconMap.put("partly-cloudy-night", R.drawable.partlycloudynight);
        iconMap.put("thunderstorm", R.drawable.thunderstrom);

        weatherimageid.setImageResource(iconMap.get(currently.getIcon()));


    }

    //this event to find error
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFailureEvent(ErrorEvents Errorevent) {
        Toast.makeText(this, Errorevent.getErrorMessage(), Toast.LENGTH_SHORT).show();

    }


    private void getweatherservices() {
        WeatherApiServiceProvider weatherApiServiceProvider = new WeatherApiServiceProvider();
        weatherApiServiceProvider.getWeather();
    }


}