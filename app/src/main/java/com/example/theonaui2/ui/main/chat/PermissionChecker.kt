package com.example.theonaui2.ui.main.chat

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

//Check camera permission and grant it
fun checkCameraPermission(context: Context): Boolean {
    return if (ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    ) {
        true
    } else {
        ActivityCompat.requestPermissions(
            (context as Activity),
            arrayOf(Manifest.permission.CAMERA),
            1
        )
        false
    }
}

// Grant camera permission
fun grantCameraPermission(context: Context) {
    ActivityCompat.requestPermissions(
        (context as Activity),
        arrayOf(Manifest.permission.CAMERA),
        1
    )
}

//Check storage permission and grant it
fun checkStoragePermission(context: Context): Boolean {
    return if (ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    ) {
        true
    } else {
        ActivityCompat.requestPermissions(
            (context as Activity),
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            2
        )
        false
    }
}

// Grant storage permission
fun grantStoragePermission(context: Context) {
    ActivityCompat.requestPermissions(
        (context as Activity),
        arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
        2
    )
}

