package com.example.skycast.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.skycast.models.DailyWeather
import kotlin.math.round

@Entity(tableName = "Weather")
data class Weather(
    @PrimaryKey(autoGenerate = false)
    val id:String,
    val time: String,
    val weatherCode: Double,
    val windSpeed:Double,
    val rain: Double,
    val snow: Double,
    val tempMax: Double,
    val tempMin: Double,
    val temp: Double = round(((tempMin + tempMax) / 2) * 10) / 10
)