package com.example.skycast.data.repository

import android.util.Log
import com.example.skycast.data.remote.api.ApiService
import com.example.skycast.models.NextDays
import com.example.skycast.models.TodayWeather
import com.example.skycast.models.WeatherInfo
import kotlin.Double
import kotlin.collections.List


class WeatherRepositoryImp(private val apiService: ApiService) : WeatherRepository {
    override suspend fun getTodayWeatherNetwork(): WeatherInfo? {
        try {
            val res = apiService.getWeatherData()
            res.current.windSpeed

            val todayWeather = TodayWeather(
                temp = res.current.temp,
                rain = res.current.rain,
                weatherCode = res.current.weatherCode,
                snowFall = res.current.snowFall,
                windSpeed = res.current.windSpeed,
                humidity = res.current.humidity
            )

            val nextDays = NextDays(
                time = res.daily.time,
                weatherCode = res.daily.weatherCode,
                windSpeed = res.daily.windSpeed,
                rain = res.daily.rain,
                snow = res.daily.snow,
                tempMax = res.daily.tempMax,
                tempMin = res.daily.tempMin
            )

            val weatherInfo = WeatherInfo(todayWeather, nextDays)
            return weatherInfo
        } catch (e: Exception) {
            Log.d("Error ", e.toString())
        }
        return null;
    }


}