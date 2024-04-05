package com.example.openmovie.utils

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

//TODO(MOVE STRING TO RES FILE AND SUPPORT MORE THAN ONE PERMISSION AT THE SAME TIME ALSO INCLUDE A REQUEST CODE)
class PermissionsHandler(private val activity: AppCompatActivity, val callback: (Boolean) -> Unit) {

    fun requestPermissions() {
        if (ContextCompat.checkSelfPermission(
                activity,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            callback(true)
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        activity,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    )
                ) {
                    AlertDialog.Builder(activity)
                        .setTitle("Location Permission")
                        .setMessage("Location Permission is needed in the map feature")
                        .setNegativeButton("Cancel") { dialog, _ ->
                            Toast.makeText(
                                activity,
                                "Location Permission denied",
                                Toast.LENGTH_SHORT
                            ).show()
                            dialog.dismiss()
                        }
                        .setPositiveButton("OK") { _, _ ->
                            accessFineLocationPermission.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                        }
                        .show()
                } else {
                    accessFineLocationPermission.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                }
            }
            accessFineLocationPermission.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    private val accessFineLocationPermission =
        activity.registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                callback(true)
            }
        }

}