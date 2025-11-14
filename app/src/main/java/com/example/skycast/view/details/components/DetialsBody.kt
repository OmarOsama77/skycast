package com.example.skycast.view.details.components

import android.telecom.Call
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.skycast.R

@Composable
fun DetailsBody(modifier: Modifier = Modifier) {
    DetailsItem(painterResource(R.drawable.temperature),"Temperature","26°")
    Spacer(Modifier.height(20.dp))
    DetailsItem(painterResource(R.drawable.windyspeed),"wind speed","200 km/h")
    Spacer(Modifier.height(20.dp))
    DetailsItem(painterResource(R.drawable.humidity),"Humidity","50%")
    Spacer(Modifier.height(20.dp))
    DetailsItem(painterResource(R.drawable.snow),"Snow","Snowy")
    Spacer(Modifier.height(20.dp))
    DetailsItem(painterResource(R.drawable.sun),"Weather status","Sunny")
    Spacer(Modifier.height(20.dp))
    DetailsItem(painterResource(R.drawable.rainy),"Rain","Raining")
}