package com.example.skycast.data.repository


import com.example.skycast.models.DailyWeather

interface WeatherRepository{
    suspend fun getData(): List<DailyWeather>?
    suspend fun getDataFromApi():List<DailyWeather>?
    suspend fun insertDataIntoDB(data:List<DailyWeather>)
}