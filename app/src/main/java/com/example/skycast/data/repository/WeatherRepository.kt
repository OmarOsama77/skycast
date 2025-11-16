package com.example.skycast.data.repository


import com.example.skycast.data.local.entities.Weather
import com.example.skycast.models.DailyWeather
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getData(): List<DailyWeather>?
    suspend fun getDataFromApi(): List<DailyWeather>?
    suspend fun insertDataIntoDB(data: List<DailyWeather>)
    suspend fun updateFav(dailyWeather: DailyWeather)
    fun getFav(): Flow<List<DailyWeather>>?

}