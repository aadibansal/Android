package com.example.aditya.weather_webservice;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public final String AppID = "ebfcac32bda131ed5a160f2757938396";
    public final String BASE_URL = "http://api.openweathermap.org";

    @BindView(R.id.latitudeTextView)
    TextView latitudeTextView;
    @BindView(R.id.longitudeTextView)
    TextView longitudeTextView;
    @BindView(R.id.countryTextView)
    TextView countryTextView;
    @BindView(R.id.descriptionTextView)
    TextView descriptionTextView;
    @BindView(R.id.tempTextView)
    TextView tempTextView;
    @BindView(R.id.humidityTextView)
    TextView humidityTextView;
    @BindView(R.id.tempMinTextView)
    TextView tempMinTextView;
    @BindView(R.id.tempMaxTextView)
    TextView tempMaxTextView;
    @BindView(R.id.speedTextView)
    TextView speedTextView;
    @BindView(R.id.degTextView)
    TextView degTextView;
    @BindView(R.id.dtTextView)
    TextView dtTextView;
    @BindView(R.id.nameTextView)
    TextView nameTextView;

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage(getString(R.string.fetching_weather_info));
        mProgressDialog.setCancelable(false);

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherService weatherService = retrofit.create(WeatherService.class);

        Call<WeatherInfo> weatherInfoCall = weatherService.getWeatherInfo("Banglore", AppID);
        showProgressDialog(true);
        weatherInfoCall.enqueue(new Callback<WeatherInfo>() {
            @Override
            public void onResponse(Call<WeatherInfo> call, Response<WeatherInfo> response) {
                showProgressDialog(false);
                onDisplayWeatherInfo(response.body());
                showProgressDialog(false);
            }

            @Override
            public void onFailure(Call<WeatherInfo> call, Throwable t) {
                showProgressDialog(false);
                Toast.makeText(MainActivity.this, R.string.fetching_weather_info_failure, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void onDisplayWeatherInfo(WeatherInfo weatherInfo) {
        if (weatherInfo != null) {
            latitudeTextView.setText(String.valueOf(weatherInfo.coord.lat));
            longitudeTextView.setText(String.valueOf(weatherInfo.coord.lon));

            countryTextView.setText(String.valueOf(weatherInfo.sys.country));
            descriptionTextView.setText(String.valueOf(weatherInfo.weather[0].description));
            tempTextView.setText(String.valueOf(weatherInfo.main.temp));

            humidityTextView.setText(String.valueOf(weatherInfo.main.humidity));
            tempMinTextView.setText(String.valueOf(weatherInfo.main.temp_min));
            tempMaxTextView.setText(String.valueOf(weatherInfo.main.temp_max));

            speedTextView.setText(String.valueOf(weatherInfo.wind.speed));
            nameTextView.setText(String.valueOf(weatherInfo.name));

            degTextView.setText(String.valueOf(weatherInfo.wind.deg));
            dtTextView.setText(String.valueOf(weatherInfo.dt));
        }
    }

    private void showProgressDialog(boolean show) {
        if (show) {
            if (!mProgressDialog.isShowing()) {
                mProgressDialog.show();
            }
        } else {
            if (mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
            }
        }
    }
}
