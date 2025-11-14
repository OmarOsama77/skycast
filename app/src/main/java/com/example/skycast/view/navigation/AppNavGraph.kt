package com.example.skycast.view.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.skycast.view.details.DetailsScreen
import com.example.skycast.view.fav.Favourite
import com.example.skycast.view.home.HomeScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "HomeScreen", builder = {
        composable ("HomeScreen"){
            HomeScreen(navController)
        }
        composable ("DetailsScreen"){
            DetailsScreen(navController)
        }
        composable ("FavScreen"){
            Favourite(navController)
        }
    })
}