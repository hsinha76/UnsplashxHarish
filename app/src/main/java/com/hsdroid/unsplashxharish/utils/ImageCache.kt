package com.hsdroid.unsplashxharish.utils

import android.graphics.Bitmap
import android.util.LruCache

object ImageCache {

    private val MAX_MEMORY = (Runtime.getRuntime().maxMemory() / 1024).toInt()
    private val CACHE_SIZE = MAX_MEMORY / 8

    private val lru: LruCache<String, Bitmap> = LruCache(CACHE_SIZE)

    fun saveBitmapToCache(key: String, bitmap: Bitmap) {
        lru.put(key, bitmap)
    }

    fun retrieveBitmapFromCache(key: String): Bitmap? = lru.get(key)
}
