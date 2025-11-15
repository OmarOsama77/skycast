package com.example.skycast.mappers

import com.example.skycast.data.local.entities.Weather
import com.example.skycast.models.DailyWeather

fun DailyWeatherToWeather(data: List<DailyWeather>): List<Weather> {
    val weatherData: MutableList<Weather> = MutableList(data.size) { indx ->
        Weather(
            time = data[indx].time,
            weatherCode = data[indx].weatherCode,
            windSpeed = data[indx].windSpeed,
            rain = data[indx].rain,
            snow = data[indx].snow,
            tempMax = data[indx].tempMax,
            tempMin = data[indx].tempMin
        )
    }
    return weatherData
}

fun weatherToDailyWeather(data: List<Weather>): List<DailyWeather> {
    val dailyData: MutableList<DailyWeather> = MutableList(data.size) { indx ->
        DailyWeather(
            tempMax = data[indx].tempMax,
            weatherCode = data[indx].weatherCode,
            temp = data[indx].temp,
            windSpeed = data[indx].windSpeed,
            rain = data[indx].rain,
            tempMin = data[indx].tempMin,
            time = data[indx].time,
            snow = data[indx].snow
        )
    }
    return dailyData
}