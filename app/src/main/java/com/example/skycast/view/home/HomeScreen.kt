package com.example.skycast.view.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.skycast.view.components.ItemCard
import com.example.skycast.view.home.components.HomeHeader
import com.example.skycast.view.home.components.TodayWeather


@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 35.dp, start = 10.dp, end = 10.dp)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            item { HomeHeader(navController) }
            item { TodayWeather(navController) }

            item { Spacer(Modifier.height(6.dp)) }
            item { Text("Next 7 days", fontSize = 22.sp, fontWeight = FontWeight.SemiBold) }
            item { Spacer(Modifier.height(6.dp)) }

            items(7) { indx ->
                ItemCard(navController)
            }
        }

    }


}