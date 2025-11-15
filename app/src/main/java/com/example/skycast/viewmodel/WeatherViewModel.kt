package com.example.skycast.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.skycast.data.repository.WeatherRepository
import com.example.skycast.models.DailyWeather
import kotlinx.coroutines.launch

class WeatherViewModel(private val repo: WeatherRepository) : ViewModel() {
    private var _daily = MutableLiveData<List<DailyWeather>>(null)
    val daily: LiveData<List<DailyWeather>>
        get() = _daily


    init {

        getData()

    }
    private fun getData(){
        viewModelScope.launch {
           _daily.value = repo.getData()
        }
    }

}