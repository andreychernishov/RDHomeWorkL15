package com.example.rdhomeworkl15

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Response


class MainActivity(): Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val dayTv : TextView = findViewById(R.id.day_tv)
        val tempTv : TextView = findViewById(R.id.temperature_tv)
        val windSpeedTv : TextView = findViewById(R.id.windspeed_tv)
        val windDirectionTv : TextView = findViewById(R.id.winddirection_tv)
        val retrofitBtn : Button  = findViewById(R.id.retrofit_btn)
        val rxJavaBtn : Button = findViewById(R.id.rx_java_btn)

        retrofitBtn.setOnClickListener {
            val client = ApiClient.retrofit.create(ApiInterface::class.java)
            client.getWeatherRetrofit().enqueue(object: retrofit2.Callback<WeatherResponse>{
                override fun onResponse(
                    call: Call<WeatherResponse>,
                    response: Response<WeatherResponse>
                ) {
                    if (response.isSuccessful){
                        val data = response.body()?.data
                        dayTv.text = data?.day
                        tempTv.text = data?.temperature
                        windDirectionTv.text = data?.windDirection
                        windSpeedTv.text = data?.windSpeed
                    }
                }
                override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                    Toast.makeText(this@MainActivity,"Error", Toast.LENGTH_SHORT).show()
                }
            })
        }

        rxJavaBtn.setOnClickListener {
            val client = ApiClient.rxClient.create(ApiInterface::class.java)
            client.getWeatherRx()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({result ->
                    val data = result.data
                    dayTv.text = data?.day
                    tempTv.text = data?.temperature
                    windDirectionTv.text = data?.windDirection
                    windSpeedTv.text = data?.windSpeed
                },{error ->
                    Toast.makeText(this@MainActivity,"Error", Toast.LENGTH_SHORT).show()
                })
        }
    }
}