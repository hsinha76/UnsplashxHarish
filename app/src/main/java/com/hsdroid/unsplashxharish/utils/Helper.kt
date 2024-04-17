package com.hsdroid.unsplashxharish.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL

class Helper {
    companion object {
        const val BASE_URL = "https://api.unsplash.com/"
        const val API_KEY = "YOUR_KEY_HERE"

        suspend fun downloadBitmap(url: String): Bitmap? = withContext(Dispatchers.IO) {
            try {
                URL(url).openStream().use { inputStream ->
                    return@withContext BitmapFactory.decodeStream(inputStream)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }

    }
}