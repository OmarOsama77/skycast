package com.example.skycast.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.skycast.data.repository.WeatherRepository
import com.example.skycast.models.WeatherInfo
import kotlinx.coroutines.launch

class WeatherViewModel(private val repo: WeatherRepository) : ViewModel() {
    private var _weather  = MutableLiveData<WeatherInfo>(null)
    val weather : LiveData<WeatherInfo>
        get() = _weather

    init {
        viewModelScope.launch {
            _weather.value = repo.getTodayWeatherNetwork()
        }
    }
}