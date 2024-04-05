package com.example.openmovie.location

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.openmovie.presentation.MainActivity
import com.example.openmovie.R
import com.example.openmovie.data.datasource.remote.FirestoreRepository
import com.example.openmovie.data.datasource.remote.Location
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

const val CHANNEL_ID = "openmovie:location"
@AndroidEntryPoint
class LocationService : Service() {

    @Inject
    lateinit var locationClientImpl: LocationClient

    private val scope = CoroutineScope(Dispatchers.IO)

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        createNotificationChannel()
        val notification = createNotification()
        startForeground(1, notification)
        locationClientImpl.startLocationUpdates{ latitude, longitude ->
            Log.e("location found:" , "$latitude, $longitude")
            sendLocationToDb(latitude, longitude)
        }
        return START_NOT_STICKY
    }

    private fun sendLocationToDb(latitude: Double, longitude: Double) {
        FirestoreRepository().apply {
            scope.launch {
                this@apply.saveLocation(Location(latitude, longitude))
                this@apply.getLocations {
                    println(it)
                }
            }
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                "Foreground Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(serviceChannel)
        }
    }

    private fun createNotification(): Notification {
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0, notificationIntent, FLAG_IMMUTABLE
        )

        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Getting location")
            .setContentText("Needed for the maps feature")
            .setSmallIcon(R.drawable.baseline_add_location_24)
            .setContentIntent(pendingIntent)
            .build()
    }


    override fun onDestroy() {
        super.onDestroy()
        locationClientImpl.stopLocationUpdates()
    }

}