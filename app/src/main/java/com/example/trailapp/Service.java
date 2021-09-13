package com.example.trailapp;

import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Service {

  private static Retrofit.Builder Rbuilder = new Retrofit.Builder()
          .baseUrl("https://api.weatherapi.com/")
          .addConverterFactory(GsonConverterFactory.create());

 private static Retrofit retrofit = Rbuilder.build();

 private static WeatherApi weatherApi = retrofit.create(WeatherApi.class);

  public static WeatherApi getWeatherApi(){
     return weatherApi;
  }


}
