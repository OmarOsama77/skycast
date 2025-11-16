package com.example.skycast.view.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.skycast.models.Weather
import com.example.skycast.view.details.DetailsScreen
import com.example.skycast.view.fav.Favourite
import com.example.skycast.view.home.HomeScreen
import com.example.skycast.viewmodel.WeatherViewModel

@Composable
fun AppNavGraph(navController: NavHostController,viewModel: WeatherViewModel) {


    NavHost(navController = navController, startDestination = "HomeScreen", builder = {
        composable ("HomeScreen"){
            HomeScreen(navController,viewModel)
        }
         composable ("DetailsScreen"){navBackStackEntry->
             val dailyWeather = navBackStackEntry.savedStateHandle.get<Weather>("daily")!!
             DetailsScreen(navController,dailyWeather,viewModel)
         }
        composable ("FavScreen"){
            Favourite(navController,viewModel)
        }
    })
}