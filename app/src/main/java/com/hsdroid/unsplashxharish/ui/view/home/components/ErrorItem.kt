package com.hsdroid.unsplashxharish.ui.view.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.hsdroid.unsplashxharish.data.models.ServerResponseItem

@Composable
fun ErrorItem(response: LazyPagingItems<ServerResponseItem>) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        contentAlignment = Alignment.Center
    ) {

        IconButton(onClick = { response.retry() }) {
            Icon(imageVector = Icons.Default.Refresh, contentDescription = null)
        }
    }
}