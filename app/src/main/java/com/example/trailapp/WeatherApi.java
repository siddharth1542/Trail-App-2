package com.example.trailapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WeatherApi {


    @GET("v1/current.json")
   Call<WeatherResponseModel> weatherSrch(
        @Query("key") String key,
        @Query("q") String q,
        @Query("aqi")String aqi
    );

   @GET("pincode/{pin}")
    Call<List<CheckResponseModel>> checkPincode(

            @Path("pin") int pincode
   );

}
