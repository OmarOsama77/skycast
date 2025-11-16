package com.example.skycast.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.math.round

@Entity(tableName = "Weather")
data class WeatherEntity(
    @PrimaryKey(autoGenerate = false)
    val time: String,
    val weatherCode: Double,
    val windSpeed:Double,
    val rain: Double,
    val snow: Double,
    val tempMax: Double,
    val tempMin: Double,
    val fav : Boolean,
    val temp: Double = round(((tempMin + tempMax) / 2) * 10) / 10
)