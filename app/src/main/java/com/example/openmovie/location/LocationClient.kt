package com.example.openmovie.location

interface LocationClient {
    fun startLocationUpdates(callback: (latitude: Double, longitude: Double) -> Unit)
    fun stopLocationUpdates()
}