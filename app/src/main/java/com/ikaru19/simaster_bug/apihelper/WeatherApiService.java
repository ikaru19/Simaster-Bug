package com.ikaru19.simaster_bug.apihelper;

import com.ikaru19.simaster_bug.models.weather.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApiService {
    @GET("onecall?exclude=minutely,alerts,hourly,daily&appid=70d1fd653f6460c6e4420e5b0e6763c2&lang=id")
    Call<WeatherResponse> getCurrentWeather(
                                            @Query("lat") Double lat,
                                            @Query("lon") Double lon
                                );
}