package com.example.skycast.data.remote.dto

import com.squareup.moshi.Json

data class NextDaysWeatherDto(
    val time: List<String>,
    @Json(name = "weather_code") val weatherCode: List<Double>,
    @Json(name = "wind_speed_10m_max") val windSpeed: List<Double>,
    @Json(name = "rain_sum") val rain: List<Double>,
    @Json(name = "snowfall_sum") val snow: List<Double>,
    @Json(name = "temperature_2m_max") val tempMax: List<Double>,
    @Json(name = "temperature_2m_min") val tempMin: List<Double>
)