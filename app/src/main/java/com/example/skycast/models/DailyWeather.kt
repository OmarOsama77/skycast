package com.example.skycast.models

import kotlin.math.round

data class DailyWeather(
    val time: String,
    val weatherCode: Double,
    val windSpeed:Double,
    val rain: Double,
    val snow: Double,
    val tempMax: Double,
    val tempMin: Double,
    val temp: Double = round(((tempMin + tempMax) / 2) * 100) / 100


)