package com.example.skycast.data.repository

import android.util.Log
import com.example.skycast.data.local.WeatherDataBaseDao
import com.example.skycast.data.local.entities.Weather
import com.example.skycast.data.network.ConnectivityObserver
import com.example.skycast.data.remote.api.ApiService
import com.example.skycast.mappers.dailyToWeather
import com.example.skycast.mappers.dailyWeatherToWeather
import com.example.skycast.mappers.weatherFlowToDailyWeather
import com.example.skycast.mappers.weatherToDailyWeather
import com.example.skycast.models.DailyWeather
import com.example.skycast.models.NextDays
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class WeatherRepositoryImp(
    private val apiService: ApiService,
    private val db: WeatherDataBaseDao,
    private val connectivityObserver: ConnectivityObserver
) :
    WeatherRepository {

    companion object {
        var dataComingFromApi = true
    }

    override suspend fun getData(): List<DailyWeather>? {
        try {
            val networkStatus = connectivityObserver.observer().first()

            if (networkStatus.toString() == "Available") {

                val dailyWeather: List<DailyWeather> = getDataFromApi()!!
                dataComingFromApi = true
                //caching
                insertDataIntoDB(dailyWeather)

                return dailyWeather
            } else {
                dataComingFromApi = false
                val data: List<Weather> = db.getData()
                val final = weatherToDailyWeather(data)

                return final
            }
        } catch (e: Exception) {
            Log.d("Errodsadsar", e.toString())
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
                    time = nextDays.time[i],
                    weatherCode = nextDays.weatherCode[i],
                    snow = nextDays.snow[i],
                    rain = nextDays.rain[i],
                    tempMax = nextDays.tempMax[i],
                    tempMin = nextDays.tempMin[i],
                    fav = db.getFavStatus(nextDays.time[i]),
                    windSpeed = nextDays.windSpeed[i],
                )
            }

            return dailyWeather
        } catch (e: Exception) {
            Log.d("EEEEE", e.toString())
        }
        return null
    }

    override suspend fun insertDataIntoDB(data: List<DailyWeather>) {
        val weather = dailyWeatherToWeather(data)
        db.insertData(weather)
    }

    override suspend fun updateFav(dailyWeather: DailyWeather) {
        val weather = dailyToWeather(dailyWeather)
        db.updateFav(weather)
    }

    override fun getFav(): Flow<List<DailyWeather>> {
        val data = db.getFav()
        val final: Flow<List<DailyWeather>> = weatherFlowToDailyWeather(data!!)
        return final
    }


}