package com.example.skycast.data.remote.dto

data class WeatherResponseDto(
    val current: TodayWeatherDto,
    val daily : NextDaysWeatherDto
)