package com.example.rdhomeworkl15

import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("v2/rates/bitcoin")
    fun getWeatherRetrofit() : Call<WeatherResponse>

    @GET("/v1/forecast?latitude=50.4547&longitude=30.5238&current_weather=true")
    fun getWeatherRx() : Single<WeatherResponse>
}