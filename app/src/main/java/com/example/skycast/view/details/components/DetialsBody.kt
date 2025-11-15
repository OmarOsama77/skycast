package com.example.skycast.view.details.components

import android.telecom.Call
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.skycast.R
import com.example.skycast.models.DailyWeather

@Composable
fun DetailsBody(dailyWeather: DailyWeather) {
    DetailsItem(painterResource(R.drawable.calendar),"Date","${dailyWeather.time}")
    Spacer(Modifier.height(20.dp))
    DetailsItem(painterResource(R.drawable.temperature),"Temperature","${dailyWeather.temp}°")
    Spacer(Modifier.height(20.dp))
    DetailsItem(painterResource(R.drawable.windyspeed),"wind speed","${dailyWeather.windSpeed} km/h")
    Spacer(Modifier.height(20.dp))
    DetailsItem(painterResource(R.drawable.snow),"Snow","${dailyWeather.snow}")
    Spacer(Modifier.height(20.dp))
    DetailsItem(painterResource(R.drawable.sun),"Weather status","${dailyWeather.weatherCode}")
    Spacer(Modifier.height(20.dp))
    DetailsItem(painterResource(R.drawable.rainy),"Rain","${dailyWeather.rain}")
    Spacer(Modifier.height(20.dp))
    DetailsItem(painterResource(R.drawable.temperature),"Max temp","${dailyWeather.tempMax}")
    Spacer(Modifier.height(20.dp))
    DetailsItem(painterResource(R.drawable.temperature),"Min temp","${dailyWeather.tempMin}")
}