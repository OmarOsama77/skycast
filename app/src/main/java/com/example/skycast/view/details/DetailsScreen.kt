package com.example.skycast.view.details

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.skycast.R
import com.example.skycast.view.details.components.DetailsItem

@Composable
fun DetailsScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp, start = 10.dp, end = 10.dp)
    ) {


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
            Text("November 15", fontSize = 22.sp)
        }
        Spacer(Modifier.height(20.dp))
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text("Weather Details", fontSize = 22.sp, fontWeight = FontWeight.Bold)
            IconButton({}) {
                Icon(Icons.Default.FavoriteBorder, contentDescription = null)
            }
        }
        Spacer(Modifier.height(20.dp))
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
}