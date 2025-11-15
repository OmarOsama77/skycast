package com.example.skycast.data.repository


import com.example.skycast.models.WeatherInfo

interface WeatherRepository{
    suspend fun getTodayWeatherNetwork(): WeatherInfo?
}