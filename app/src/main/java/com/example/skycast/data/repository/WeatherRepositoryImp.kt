package com.example.skycast.data.repository

import android.util.Log
import com.example.skycast.data.local.WeatherDataBaseDao
import com.example.skycast.data.local.entities.WeatherEntity
import com.example.skycast.data.network.ConnectivityObserver
import com.example.skycast.data.remote.api.ApiService
import com.example.skycast.models.NextDays
import com.example.skycast.models.Weather
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class WeatherRepositoryImp(
    private val apiService: ApiService,
    private val db: WeatherDataBaseDao,
    private val connectivityObserver: ConnectivityObserver
) :
    WeatherRepository {

    companion object {
        var dataComingFromApi = true
    }

    override suspend fun getData(today: String): List<Weather>? {
        try {
            val networkStatus = connectivityObserver.observer().first()

            if (networkStatus.toString() == "Available") {

                val dailyWeather: List<Weather> = getDataFromApi()!!
                dataComingFromApi = true

                //caching
                insertDataIntoDB(dailyWeather,today)
                val data = db.getData()
                val res: List<Weather> = data.map { entity ->
                    Weather(
                        time = entity.time,
                        weatherCode = entity.weatherCode,
                        windSpeed = entity.windSpeed,
                        rain = entity.rain,
                        snow = entity.snow,
                        tempMax = entity.tempMax,
                        tempMin = entity.tempMin,
                        fav = entity.fav
                    )
                }


                return res
            } else {
                dataComingFromApi = false
                val data: List<WeatherEntity> = db.getData()
                val final: List<Weather> = data.map { entity ->
                    Weather(
                        time = entity.time,
                        weatherCode = entity.weatherCode,
                        windSpeed = entity.windSpeed,
                        rain = entity.rain,
                        snow = entity.snow,
                        tempMax = entity.tempMax,
                        tempMin = entity.tempMin,
                        fav = entity.fav
                    )
                }

                return final
            }
        } catch (e: Exception) {
            Log.d("Errorr", e.toString())
        }
        return null;
    }

    override suspend fun getDataFromApi(): List<Weather>? {
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

            val dailyWeather: MutableList<Weather> = MutableList(nextDays.rain.size) { i ->
                Weather(
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
            Log.d("EEEEE", e.toString())
        }
        return null
    }

    override suspend fun insertDataIntoDB(data: List<Weather>,today:String) {
         //we need to convert to weatherEntity to add to db

         val weatherEntities : List<WeatherEntity> = data.map {daily->
             WeatherEntity(
                 time = daily.time,
                 weatherCode = daily.weatherCode,
                 windSpeed = daily.windSpeed,
                 rain = daily.rain,
                 snow = daily.snow,
                 tempMax = daily.tempMax,
                 tempMin = daily.tempMin,
                 fav = false
             )
         }
        db.insertData(weatherEntities)
       db.deleteOldData(today)
    }

    override suspend fun updateFav(weather: Weather) {
        val updated : WeatherEntity = WeatherEntity(
             time = weather.time,
            fav = weather.fav,
            rain = weather.rain,
            weatherCode = weather.weatherCode,
            temp = weather.temp,
            snow = weather.snow,
            tempMax = weather.tempMax,
            tempMin = weather.tempMin,
            windSpeed = weather.windSpeed
        )
        db.updateFav(updated)
    }





    override fun getFav(): Flow<List<Weather>>? {
        val data: Flow<List<WeatherEntity>> = db.getFav()!!

        val flow: Flow<List<Weather>> = data.map { list ->
            list.map { entity ->
                Weather(
                    time = entity.time,
                    weatherCode = entity.weatherCode,
                    windSpeed = entity.windSpeed,
                    rain = entity.rain,
                    snow = entity.snow,
                    tempMax = entity.tempMax,
                    tempMin = entity.tempMin,
                    fav = entity.fav
                )
            }
        }

        return flow
    }



}