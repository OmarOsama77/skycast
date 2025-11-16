package com.example.skycast.view.fav

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.skycast.models.Weather
import com.example.skycast.view.components.ItemCard
import com.example.skycast.view.fav.components.FavHeader
import com.example.skycast.viewmodel.WeatherViewModel

@Composable
fun Favourite(navController: NavController,viewModel: WeatherViewModel) {
    val fav = viewModel.fav!!.collectAsState(emptyList())
    Column(

        modifier = Modifier.padding(top = 32.dp, start = 15.dp, end = 15.dp)
    ) {
        LazyColumn (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ){
            item {
               FavHeader(navController)
            }

            items(fav.value.size) {indx->
                ItemCard(navController, Weather(
                    fav.value[indx].time,
                    fav.value[indx].weatherCode,
                    fav.value[indx].windSpeed,
                    fav.value[indx].rain,
                    fav.value[indx].snow,
                    fav.value[indx].tempMax,
                    fav.value[indx].fav,
                    fav.value[indx].tempMin
                ))
            }
        }
    }
}