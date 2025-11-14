package com.example.skycast.view.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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

@Composable
fun TodayWeather(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .shadow(elevation = 3.dp, shape = RoundedCornerShape(12.dp))
            .background(Color.White)
            .clickable{
                navController.navigate("DetailsScreen")
            }
    ) {
        Row(
            modifier = Modifier
                .padding(top = 10.dp, start = 10.dp, end = 10.dp, bottom = 10.dp)
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    "28",
                    fontSize = 50.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier.size(22.dp),
                        painter = painterResource(R.drawable.windyspeed),
                        contentDescription = null,
                    )
                    Spacer(Modifier.width(5.dp))
                    Text("15 km/h")

                    Spacer(Modifier.width(12.dp))
                    Image(
                        modifier = Modifier.size(22.dp),
                        painter = painterResource(R.drawable.humidity),
                        contentDescription = null,
                    )
                    Spacer(Modifier.width(5.dp))
                    Text("45%")
                }
            }

            Image(
                modifier = Modifier
                    .size(70.dp)
                    .align(Alignment.CenterVertically),
                painter = painterResource(R.drawable.sun),
                contentDescription = null
            )
        }
    }
}