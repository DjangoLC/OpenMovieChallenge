package com.example.openmovie.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

fun Context.hasLocationPermission() = ContextCompat.checkSelfPermission(
    this,
    Manifest.permission.ACCESS_COARSE_LOCATION
) == PackageManager.PERMISSION_GRANTED &&
        ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

fun Context.hasStoragePermission() = ContextCompat.checkSelfPermission(
    this,
    Manifest.permission.READ_EXTERNAL_STORAGE
) == PackageManager.PERMISSION_GRANTED
