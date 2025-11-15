package com.example.skycast.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class NetworkConnectivityObserver(private val context: Context) : ConnectivityObserver {

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override fun observer(): Flow<ConnectivityObserver.Status> = callbackFlow {

        val activeNetwork = connectivityManager.activeNetwork
        val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
        val initialStatus = if (networkCapabilities != null) {
            ConnectivityObserver.Status.Available
        } else {
            ConnectivityObserver.Status.Unavailable
        }
        trySend(initialStatus)

        val callback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                trySend(ConnectivityObserver.Status.Available)
            }

            override fun onLosing(network: Network, maxMsToLive: Int) {
                trySend(ConnectivityObserver.Status.Losing)
            }

            override fun onLost(network: Network) {
                trySend(ConnectivityObserver.Status.Lost)
            }
        }

        connectivityManager.registerDefaultNetworkCallback(callback)
        awaitClose {
            connectivityManager.unregisterNetworkCallback(callback)
        }
    }.distinctUntilChanged()
}
