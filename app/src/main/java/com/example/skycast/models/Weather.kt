package com.example.skycast.models

data class Weather(
    val temperature_2m : Double,
    val rain:Double,
    val weatherCode:Double,
    val snowfall : Double,
    val windSpeed:Double,
    val humidity:Double
)