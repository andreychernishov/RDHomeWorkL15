package com.example.rdhomeworkl15

import com.google.gson.annotations.SerializedName

data class WeatherResponse(val data: Data)

data class Data(
  @SerializedName("id")  val day: String,
  @SerializedName("symbol")  val temperature: String,
  @SerializedName("type")  val windSpeed: String,
  @SerializedName("rateUsd")  val windDirection: String
//
//  @SerializedName("is_day")  val day: String,
//@SerializedName("temperature")  val temperature: String,
//@SerializedName("windspeed")  val windSpeed: String,
//@SerializedName("winddirection")  val windDirection: String
//
)