package com.example.skycast.mappers

import com.example.skycast.data.local.entities.Weather
import com.example.skycast.models.DailyWeather
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


fun dailyWeatherToWeather(data: List<DailyWeather>): List<Weather> {
    val weatherData: MutableList<Weather> = MutableList(data.size) { indx ->
        Weather(
            time = data[indx].time,
            weatherCode = data[indx].weatherCode,
            windSpeed = data[indx].windSpeed,
            rain = data[indx].rain,
            snow = data[indx].snow,
            tempMax = data[indx].tempMax,
            tempMin = data[indx].tempMin,
            fav = data[indx].fav
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
            snow = data[indx].snow,
            fav = data[indx].fav
        )
    }
    return dailyData
}

fun weatherFlowToDailyWeather(data: Flow<List<Weather>>): Flow<List<DailyWeather>> {
    return data.map { list ->
        list.map { weather ->
            DailyWeather(
                tempMax = weather.tempMax,
                weatherCode = weather.weatherCode,
                temp = weather.temp,
                windSpeed = weather.windSpeed,
                rain = weather.rain,
                tempMin = weather.tempMin,
                time = weather.time,
                snow = weather.snow,
                fav = weather.fav
            )
        }
    }
}

fun dailyToWeather(dailyWeather: DailyWeather): Weather {
    return Weather(

        rain = dailyWeather.rain,
        fav = dailyWeather.fav,
        weatherCode = dailyWeather.weatherCode,
        snow = dailyWeather.snow,
        time = dailyWeather.time,
        temp = dailyWeather.temp,
        tempMax = dailyWeather.tempMax,
        windSpeed = dailyWeather.windSpeed,
        tempMin = dailyWeather.tempMin
    )
}
