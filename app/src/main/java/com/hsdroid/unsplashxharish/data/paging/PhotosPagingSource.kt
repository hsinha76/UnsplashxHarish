package com.hsdroid.unsplashxharish.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.hsdroid.unsplashxharish.data.models.ServerResponseItem
import com.hsdroid.unsplashxharish.data.repository.PhotosRepository
import com.hsdroid.unsplashxharish.utils.Helper.Companion.API_KEY
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PhotosPagingSource @Inject constructor(private val photosRepository: PhotosRepository) :
    PagingSource<Int, ServerResponseItem>() {
    override fun getRefreshKey(state: PagingState<Int, ServerResponseItem>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ServerResponseItem> {
        val page = params.key ?: 1

        return try {
            val response = photosRepository.getPhotos(API_KEY, page, params.loadSize)
            LoadResult.Page(
                data = response,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}