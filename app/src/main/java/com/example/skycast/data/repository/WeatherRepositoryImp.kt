package com.example.skycast.data.repository

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.skycast.data.local.WeatherDataBaseDao
import com.example.skycast.data.local.entities.Weather
import com.example.skycast.data.network.ConnectivityObserver
import com.example.skycast.data.network.NetworkConnectivityObserver
import com.example.skycast.data.remote.api.ApiService
import com.example.skycast.mappers.DailyWeatherToWeather
import com.example.skycast.mappers.weatherToDailyWeather
import com.example.skycast.models.DailyWeather
import com.example.skycast.models.NextDays
import kotlinx.coroutines.flow.first
import java.util.UUID

class WeatherRepositoryImp(
    private val apiService: ApiService,
    private val db: WeatherDataBaseDao,
    private val connectivityObserver: ConnectivityObserver
) :
    WeatherRepository {


    override suspend fun getData(): List<DailyWeather>? {
        try {
            val networkStatus = connectivityObserver.observer().first()

            if (networkStatus.toString() == "Available") {

                val dailyWeather: List<DailyWeather> = getDataFromApi()!!
                //cashing
                insertDataIntoDB(dailyWeather)
                return dailyWeather
            } else {

                val data: List<Weather> = db.getData()
                val final = weatherToDailyWeather(data)

                return final
            }
        } catch (e: Exception) {
            Log.d("Error", e.toString())
        }
        return null;
    }

    override suspend fun getDataFromApi(): List<DailyWeather>? {
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
                    id = UUID.randomUUID().toString(),
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
            return null
        }
    }

    override suspend fun insertDataIntoDB(data: List<DailyWeather>) {
        val weather = DailyWeatherToWeather(data)
        db.insertData(weather)
    }


}