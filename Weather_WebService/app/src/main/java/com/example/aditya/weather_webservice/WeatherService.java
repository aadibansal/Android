package com.example.aditya.weather_webservice;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Aditya on 2/8/2017.
 */

public interface WeatherService {

    @GET("data/2.5/weather")
    Call<WeatherInfo> getWeatherInfo(@Query("q") String city, @Query("AppID") String appId);
}
