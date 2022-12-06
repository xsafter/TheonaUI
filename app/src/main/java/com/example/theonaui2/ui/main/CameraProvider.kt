package com.example.theonaui2.ui.main

import android.content.Context
import android.os.Environment
import java.io.File

public fun getPhotoFile(fileName: String, context: Context): File {
    val directoryStorage = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    return File.createTempFile(fileName, ".jpg", directoryStorage)
}
