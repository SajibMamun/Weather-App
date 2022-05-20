package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weatherapp.Model.Currently;
import com.example.weatherapp.Model.Weather;
import com.example.weatherapp.Sevices.WeatherAPiServices;
import com.example.weatherapp.Sevices.WeatherApiServiceProvider;

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


        WeatherApiServiceProvider weatherApiServiceProvider = new WeatherApiServiceProvider();
        Callback callback = new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                if (response.code() == 200) {
                    Currently currently = response.body().getCurrently();

                    temptextView.setText(String.valueOf(currently.getTemperature()));
                }
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        };


        weatherApiServiceProvider.getWeather(callback);


    }
}