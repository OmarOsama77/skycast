package com.example.skycast

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.skycast.data.local.WeatherDataBase
import com.example.skycast.data.network.ConnectivityObserver
import com.example.skycast.data.network.NetworkConnectivityObserver
import com.example.skycast.data.remote.api.WeatherApi
import com.example.skycast.data.repository.WeatherRepositoryImp
import com.example.skycast.view.navigation.AppNavGraph
import com.example.skycast.viewmodel.WeatherViewModel
import com.example.skycast.viewmodel.WeatherViewModelFac


class MainActivity : ComponentActivity() {
    private lateinit var connectivityObserver: ConnectivityObserver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        connectivityObserver = NetworkConnectivityObserver(applicationContext)

        enableEdgeToEdge()
        setContent {

            val repo = WeatherRepositoryImp(
                WeatherApi.retrofitService,
                WeatherDataBase.getInstance(applicationContext).weatherDataBaseDao,
                connectivityObserver
            )
            val factory = WeatherViewModelFac(repo)
            val viewModel: WeatherViewModel =
                ViewModelProvider(this, factory)[WeatherViewModel::class.java]

            val navController = rememberNavController()
            AppNavGraph(navController, viewModel)
        }
    }
}

