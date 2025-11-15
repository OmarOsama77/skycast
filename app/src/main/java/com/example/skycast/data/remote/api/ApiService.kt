package com.example.skycast.data.remote.api

import com.example.skycast.data.remote.dto.WeatherResponseDto
import com.example.skycast.data.remote.dto.TodayWeatherDto
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val baseUrl =
    "https://api.open-meteo.com"

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(baseUrl)
    .build()

interface ApiService {
    @GET("/v1/forecast")

    suspend fun getWeatherData(
        @Query("latitude") latitude: Double = 30.0625,
        @Query("longitude") longitude: Double = 30.0625,
        @Query("current") current: String = "temperature_2m,rain,snowfall,relative_humidity_2m,weather_code,wind_speed_10m",
        @Query("daily") daily : String = "weather_code,wind_speed_10m_max,rain_sum,snowfall_sum,temperature_2m_max,temperature_2m_min"
    ): WeatherResponseDto


}

object WeatherApi {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}