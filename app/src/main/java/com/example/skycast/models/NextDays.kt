package com.example.skycast.models

data class NextDays(
    val time: List<String>,
    val weatherCode: List<Double>,
    val windSpeed: List<Double>,
    val rain: List<Double>,
    val snow: List<Double>,
    val tempMax: List<Double>,
    val tempMin: List<Double>
)