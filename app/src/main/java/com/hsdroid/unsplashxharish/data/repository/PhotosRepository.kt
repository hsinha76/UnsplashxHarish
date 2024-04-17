package com.hsdroid.unsplashxharish.data.repository

import com.hsdroid.unsplashxharish.data.models.ServerResponseItem
import com.hsdroid.unsplashxharish.data.network.APIService
import kotlinx.coroutines.delay
import javax.inject.Inject

class PhotosRepository @Inject constructor(private val apiService: APIService) {

    suspend fun getPhotos(clientId: String, page: Int, itemCount: Int): List<ServerResponseItem> {
        delay(2000)
        return apiService.getImages(clientId, page, itemCount)
    }

}