package com.example.skycast.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.skycast.R
import com.example.skycast.models.DailyWeather

@Composable
fun ItemCard(navController: NavController,dailyWeather: DailyWeather) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .shadow(elevation = 3.dp, shape = RoundedCornerShape(12.dp))
            .background(Color.White)
            .clickable {
                navController.navigate("DetailsScreen")

                navController.currentBackStackEntry
                    ?.savedStateHandle
                    ?.set("daily", dailyWeather)
            }

    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row {
                    Image(
                        modifier = Modifier.size(30.dp),
                        painter = painterResource(R.drawable.sun),
                        contentDescription = null
                    )
                    Spacer(Modifier.width(12.dp))
                    Column {
                        Text(dailyWeather.day)
                        Text(dailyWeather.time)
                    }
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        modifier = Modifier.size(17.dp),
                        painter = painterResource(R.drawable.windyspeed),
                        contentDescription = null
                    )
                    Text("${dailyWeather.windSpeed}")
                    Spacer(Modifier.width(12.dp))
                    Text("${dailyWeather.temp}°", fontSize = 22.sp, fontWeight = FontWeight.Bold)
                }

            }

        }
    }
}