package com.example.skycast.view.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.skycast.data.repository.WeatherRepositoryImp
import com.example.skycast.view.components.ItemCard
import com.example.skycast.view.home.components.HomeHeader
import com.example.skycast.view.home.components.LoadingIndicator
import com.example.skycast.view.home.components.TodayWeather
import com.example.skycast.viewmodel.WeatherViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState


@Composable
fun HomeScreen(navController: NavController, viewModel: WeatherViewModel) {
    val weather = viewModel.daily.observeAsState()
    val isRefreshing = remember { mutableStateOf(false) }
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing.value)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 35.dp, start = 10.dp, end = 10.dp)
    ) {
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = {
                viewModel.getData()
            }
        ){
            if (weather.value == null) {
                LoadingIndicator()
            } else {

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    contentPadding = PaddingValues(bottom = 16.dp)
                ) {
                    item { HomeHeader(navController, weather.value!![0].time) }
                    item { TodayWeather(navController, viewModel,weather.value!![0]) }

                    item { Spacer(Modifier.height(6.dp)) }
                    if(WeatherRepositoryImp.dataComingFromApi){
                        item { Text("Today and Next 6 days", fontSize = 22.sp, fontWeight = FontWeight.SemiBold) }
                    }else{
                        item { Text("Room data", fontSize = 22.sp, fontWeight = FontWeight.SemiBold) }
                    }
                    item { Spacer(Modifier.height(6.dp)) }

                    items(weather.value?.size ?: 0) { indx ->
                        ItemCard(
                            navController,
                            weather.value!![indx]
                        )
                    }
                }
            }
        }
    }
}