package com.example.skycast.data.repository


import com.example.skycast.models.DailyWeather

interface WeatherRepository{
    suspend fun getTodayWeatherNetwork(): List<DailyWeather>?
}