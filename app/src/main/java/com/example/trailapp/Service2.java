package com.example.trailapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Service2 {


    private static Retrofit.Builder R2builder = new Retrofit.Builder()
            .baseUrl("https://api.postalpincode.in/")
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = R2builder.build();

    private static WeatherApi checkApi = retrofit.create(WeatherApi.class);

    public static WeatherApi getCheckApi(){
        return checkApi;
    }




}
