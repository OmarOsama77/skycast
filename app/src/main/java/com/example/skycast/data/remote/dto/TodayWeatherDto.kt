package com.example.skycast.data.remote.dto

import com.squareup.moshi.Json


data class TodayWeatherDto(
    @Json(name = "temperature_2m") val temp: Double,
    val rain: Double,
    @Json(name = "weather_code") val weatherCode: Double,
    @Json(name = "snowfall") val snowFall: Double,
    @Json(name = "wind_speed_10m") val windSpeed: Double,
    @Json(name = "relative_humidity_2m") val humidity: Double,
)