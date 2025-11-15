package com.example.skycast.data.repository

import android.util.Log
import com.example.skycast.data.remote.api.ApiService
import com.example.skycast.models.DailyWeather
import com.example.skycast.models.NextDays


class WeatherRepositoryImp(private val apiService: ApiService) : WeatherRepository {
    override suspend fun getTodayWeatherNetwork(): List<DailyWeather>? {
        try {
            val res = apiService.getWeatherData()

            val nextDays = NextDays(
                time = res.daily.time,
                weatherCode = res.daily.weatherCode,
                windSpeed = res.daily.windSpeed,
                rain = res.daily.rain,
                snow = res.daily.snow,
                tempMax = res.daily.tempMax,
                tempMin = res.daily.tempMin
            )

            val dailyWeather: MutableList<DailyWeather> = MutableList(nextDays.rain.size) { i ->
                DailyWeather(
                    time = nextDays.time[i],
                    weatherCode = nextDays.weatherCode[i],
                    snow = nextDays.snow[i],
                    rain = nextDays.rain[i],
                    tempMax = nextDays.tempMax[i],
                    tempMin = nextDays.tempMin[i],
                    windSpeed = nextDays.windSpeed[i],
                )
            }

         return dailyWeather
        } catch (e: Exception) {
            Log.d("Error ", e.toString())
        }
        return null;
    }


}