package com.dudnyk.framework.flickrgallery.data.repository.search

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dudnyk.framework.flickrgallery.common.Constants
import com.dudnyk.framework.flickrgallery.common.utils.PagingUtils.getEmptyPage
import com.dudnyk.framework.flickrgallery.data.model.dto.photo.toPhoto
import com.dudnyk.framework.flickrgallery.data.remote.FlickrApi
import com.dudnyk.framework.flickrgallery.domain.model.Photo
import retrofit2.HttpException

class PhotoPagingSource(
    private val api: FlickrApi,
    private val photoGroupId: String
) : PagingSource<Int, Photo>() {

    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        if (photoGroupId.isBlank()) {
            return getEmptyPage()
        }
        val page = params.key ?: 1
        val pageSize = params.loadSize.coerceAtMost(Constants.MAX_PAGE_SIZE)
        val response =
            api.getPhotosByGroupId(groupId = photoGroupId, page = page, pageSize = pageSize)
        if (response.isSuccessful) {
            val listOfPhotos = response.body()?.let { photoGroupResponse ->
                photoGroupResponse.photos.listOfPhotos.map { it.toPhoto() }
            } ?: emptyList()

            if (listOfPhotos.isEmpty()) {
                return getEmptyPage()
            }

            val nextKey = if (pageSize > listOfPhotos.size) null else page + 1
            val prevKey = if (page == 1) null else page + 1
            return LoadResult.Page(listOfPhotos, prevKey, nextKey)
        } else {
            return LoadResult.Error(HttpException(response))
        }
    }
}