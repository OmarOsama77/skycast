package com.example.skycast.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.skycast.data.repository.WeatherRepository
import com.example.skycast.models.Weather
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class WeatherViewModel(private val repo: WeatherRepository) : ViewModel() {
    private var _daily = MutableLiveData<List<Weather>>(null)
    val daily: LiveData<List<Weather>>
        get() = _daily

    var fav: Flow<List<Weather>>? = repo.getFav()

    init {
        getData()

    }

     fun getData() {
        viewModelScope.launch {
            _daily.value = repo.getData()
        }
    }
    fun updateFav(dailyWeather: Weather) {
        viewModelScope.launch {
            repo.updateFav(dailyWeather)
        }
    }

}