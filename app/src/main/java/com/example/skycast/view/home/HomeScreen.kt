package com.example.skycast.view.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.skycast.view.components.ItemCard
import com.example.skycast.view.home.components.HomeHeader
import com.example.skycast.view.home.components.TodayWeather
import com.example.skycast.viewmodel.WeatherViewModel


@Composable
fun HomeScreen(navController: NavController, viewModel: WeatherViewModel) {
    val weather = viewModel.daily.observeAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 35.dp, start = 10.dp, end = 10.dp)
    ) {
        if (weather.value == null) {
            Text("No data")
        } else {

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                item { HomeHeader(navController, weather.value!![0].time) }
                item { TodayWeather(navController, viewModel) }

                item { Spacer(Modifier.height(6.dp)) }
                item { Text("Next 7 days", fontSize = 22.sp, fontWeight = FontWeight.SemiBold) }
                item { Spacer(Modifier.height(6.dp)) }

                items(weather.value?.size ?: 0) { indx ->
                    ItemCard(
                        navController,
                        weather.value!![indx].temp,
                        weather.value!![indx].windSpeed,
                        weather.value!![indx].time,
                    )
                }
            }
        }
    }
}