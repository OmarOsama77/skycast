package com.example.skycast.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlin.math.round

@Parcelize
data class DailyWeather(
    val time: String,
    val weatherCode: Double,
    val windSpeed:Double,
    val rain: Double,
    val snow: Double,
    val tempMax: Double,
    var fav: Boolean,
    val tempMin: Double,
    val day: String,
    val temp: Double = round(((tempMin + tempMax) / 2) * 10) / 10


) : Parcelable

