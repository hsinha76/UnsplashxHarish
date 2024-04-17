package com.hsdroid.unsplashxharish.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.hsdroid.unsplashxharish.data.models.ServerResponseItem
import com.hsdroid.unsplashxharish.data.paging.PhotosPagingSource
import com.hsdroid.unsplashxharish.data.repository.PhotosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val photosRepository: PhotosRepository) :
    ViewModel() {

    fun getImages(): Flow<PagingData<ServerResponseItem>> =
        Pager(
            config = PagingConfig(
                pageSize = 15,
                enablePlaceholders = false,
                initialLoadSize = 15,
                prefetchDistance = 8
            )
        ) {
            PhotosPagingSource(photosRepository)
        }.flow.cachedIn(viewModelScope)
}