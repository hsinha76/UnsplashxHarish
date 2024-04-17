package com.hsdroid.unsplashxharish.ui.view.home.components

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.sp
import com.hsdroid.unsplashxharish.utils.Helper.Companion.downloadBitmap
import com.hsdroid.unsplashxharish.utils.ImageCache

@Composable
fun ImageItem(
    imageUrl: String, modifier: Modifier = Modifier
) {
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }

    LaunchedEffect(imageUrl) {
        bitmap = ImageCache.retrieveBitmapFromCache(imageUrl) ?: downloadBitmap(imageUrl)
        if (bitmap == null) {
            bitmap = downloadBitmap(imageUrl)
            bitmap?.let { ImageCache.saveBitmapToCache(imageUrl, it) }
        }
    }

    bitmap?.asImageBitmap()?.let {
        Image(
            bitmap = it,
            contentDescription = "Loaded Image",
            modifier = modifier,
            contentScale = ContentScale.Crop
        )
    } ?: Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Text(text = "Loading..", fontSize = 14.sp)
    }

    DisposableEffect(imageUrl) {
        onDispose {
            bitmap?.recycle()
            bitmap = null
        }
    }
}