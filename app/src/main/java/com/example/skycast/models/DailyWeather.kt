package com.example.skycast.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlin.math.round
@Parcelize
data class DailyWeather(
    val id : String,
    val time: String,
    val weatherCode: Double,
    val windSpeed:Double,
    val rain: Double,
    val snow: Double,
    val tempMax: Double,
    val tempMin: Double,
    val temp: Double = round(((tempMin + tempMax) / 2) * 10) / 10


) : Parcelable