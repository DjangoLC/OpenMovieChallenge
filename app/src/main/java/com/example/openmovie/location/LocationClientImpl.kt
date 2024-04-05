package com.example.openmovie.location

import android.annotation.SuppressLint
import android.app.Service
import android.content.Context
import android.location.LocationManager
import android.os.Looper
import com.example.openmovie.utils.hasLocationPermission
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

//TODO(CRATE A RES PROVIDER TO AVOID HARDCODE STRINGS)
@Singleton
class LocationClientImpl @Inject constructor(
    private val context: Context,
    private val client: FusedLocationProviderClient
) : LocationClient {

    private lateinit var locationCallback: LocationCallback

    @SuppressLint("MissingPermission")
    override fun startLocationUpdates(callback: (latitude: Double, longitude: Double) -> Unit) {

        if (!context.hasLocationPermission()) {
            throw Exception("has not locations permissions")
        }

        val locationManager = context.getSystemService(Service.LOCATION_SERVICE) as LocationManager
        val gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        val networkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

        if (!gpsEnabled && networkEnabled) {
            throw Exception("has not providers for gps enabled")
        }

        val request = LocationRequest.create()
            .setInterval(50000)
            .setFastestInterval(50000)

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(result: LocationResult) {
                super.onLocationResult(result)
                result.locations.lastOrNull()?.let {
                    callback(it.latitude, it.longitude)
                }
            }
        }

        client.requestLocationUpdates(request, locationCallback, Looper.getMainLooper())
    }

    override fun stopLocationUpdates() {
        client.removeLocationUpdates(locationCallback)
    }
}