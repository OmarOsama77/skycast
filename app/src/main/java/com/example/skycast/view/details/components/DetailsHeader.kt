package com.example.skycast.view.details.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.skycast.models.Weather

@Composable
fun DetailsHeader(navController: NavController,weather: Weather) {
    Box(modifier = Modifier.clickable{
        navController.popBackStack()
    }){
        Icon(
            modifier = Modifier
                .size(30.dp)
                .fillMaxSize(),
            imageVector = Icons.Default.KeyboardArrowLeft,
            contentDescription = null
        )
    }


    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Cairo", fontSize = 30.sp, fontWeight = FontWeight.Bold)
        Text("${weather.day} ${weather.time}", fontSize = 22.sp)
    }
}