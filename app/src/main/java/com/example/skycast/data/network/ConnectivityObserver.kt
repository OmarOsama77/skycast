package com.example.skycast.data.network

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver{
    fun observer(): Flow<Status>

    enum class Status{
        Available,Unavailable,Lost,Losing
    }
}