package com.example.skycast.data.repository


import com.example.skycast.models.Weather
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getData(): List<Weather>?
    suspend fun getDataFromApi(): List<Weather>?
    suspend fun insertDataIntoDB(data: List<Weather>)
    suspend fun updateFav(weather: Weather)
    fun getFav(): Flow<List<Weather>>?

}