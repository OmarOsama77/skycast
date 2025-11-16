package com.example.skycast.view.details

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.skycast.models.Weather
import com.example.skycast.view.details.components.DetailsBody
import com.example.skycast.view.details.components.DetailsHeader
import com.example.skycast.viewmodel.WeatherViewModel

@Composable
fun DetailsScreen(navController: NavController, dailyWeather: Weather, viewModel: WeatherViewModel) {
    val scrollState = rememberScrollState()
    val favState = remember { mutableStateOf(dailyWeather.fav) }
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp, start = 10.dp, end = 10.dp, bottom = 10.dp)
            .verticalScroll(scrollState)
    ) {

       DetailsHeader(navController, weather = dailyWeather)
        Spacer(Modifier.height(20.dp))
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text("Weather Details", fontSize = 22.sp, fontWeight = FontWeight.Bold)
            IconButton({
                dailyWeather.fav = !dailyWeather.fav
                favState.value=!favState.value
                viewModel.updateFav(dailyWeather)

                Toast.makeText(
                    context,
                    if (favState.value) "Added to favorites" else "Removed from favorites",
                    Toast.LENGTH_SHORT
                ).show()


            }) {
                if(favState.value){
                    Icon(Icons.Default.Favorite, contentDescription = null)
                }else{
                    Icon(Icons.Default.FavoriteBorder, contentDescription = null)
                }
            }
        }
        Spacer(Modifier.height(20.dp))
        DetailsBody(dailyWeather)
    }
}