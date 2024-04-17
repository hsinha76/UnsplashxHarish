package com.hsdroid.unsplashxharish.ui.view.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.hsdroid.unsplashxharish.ui.view.home.components.ErrorItem
import com.hsdroid.unsplashxharish.ui.view.home.components.ImageItem
import com.hsdroid.unsplashxharish.ui.view.home.components.LoadingItem
import com.hsdroid.unsplashxharish.ui.viewModel.MainViewModel

@Composable
fun HomeScreen(mainViewModel: MainViewModel, modifier: Modifier = Modifier) {

    val response = mainViewModel.getImages().collectAsLazyPagingItems()

    LazyVerticalGrid(
        columns = GridCells.Fixed(3), modifier = modifier.fillMaxSize()
    ) {
        items(response.itemCount) {
            response[it]?.let { url ->
                ImageItem(
                    url.urls.small, modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(4.dp)
                )
            }
        }

        if (response.loadState.refresh is LoadState.Loading ||
            response.loadState.append is LoadState.Loading
        ) {
            item {
                LoadingItem()
            }
        }

        if (response.loadState.refresh is LoadState.Error ||
            response.loadState.append is LoadState.Error
        ) {
            item {
                ErrorItem(response)
            }
        }

    }
}