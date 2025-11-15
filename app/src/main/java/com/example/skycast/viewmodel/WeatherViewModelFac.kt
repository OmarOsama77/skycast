package com.example.skycast.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.skycast.data.repository.WeatherRepository

class WeatherViewModelFac (private val repo: WeatherRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WeatherViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}