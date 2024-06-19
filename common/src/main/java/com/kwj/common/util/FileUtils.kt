package com.kwj.common.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.net.URL

suspend fun downloadAndSaveImage(context: Context, imageUrl: String, fileName: String): String? {
    return withContext(Dispatchers.IO) {
        try {
            val url = URL(imageUrl)
            val bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream())

            saveImageToFile(context, bitmap, fileName)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }
}

fun saveImageToFile(context: Context, bitmap: Bitmap, fileName: String): String? {
    val directory = context.getExternalFilesDir(null)
    val file = File(directory, fileName)
    return try {
        val fos = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)
        fos.flush()
        fos.close()
        file.absolutePath
    } catch (e: IOException) {
        e.printStackTrace()
        null
    }
}