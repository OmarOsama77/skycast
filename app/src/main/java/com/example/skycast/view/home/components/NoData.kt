package com.example.skycast.view.home.components



import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun NoData(modifier: Modifier = Modifier) {
    Box( modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center){
        Text("It's your first time opening the app please connect to internet")
    }
}