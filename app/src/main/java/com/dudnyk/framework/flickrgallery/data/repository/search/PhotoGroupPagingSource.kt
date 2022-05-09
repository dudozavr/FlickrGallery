package com.dudnyk.framework.flickrgallery.data.repository.search

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dudnyk.framework.flickrgallery.common.Constants.MAX_PAGE_SIZE
import com.dudnyk.framework.flickrgallery.common.utils.PagingUtils.getEmptyPage
import com.dudnyk.framework.flickrgallery.data.model.dto.group.toPhotoGroup
import com.dudnyk.framework.flickrgallery.data.remote.FlickrApi
import com.dudnyk.framework.flickrgallery.domain.model.PhotoGroup
import retrofit2.HttpException

class PhotoGroupPagingSource(
    private val api: FlickrApi,
    private val queryKey: String
) : PagingSource<Int, PhotoGroup>() {

    override fun getRefreshKey(state: PagingState<Int, PhotoGroup>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PhotoGroup> {
        if (queryKey.isBlank()) {
            return getEmptyPage()
        }
        val page = params.key ?: 1
        val pageSize = params.loadSize.coerceAtMost(MAX_PAGE_SIZE)
        val response =
            api.getGroupsByQueryKey(queryKeyText = queryKey, page = page, pageSize = pageSize)
        if (response.isSuccessful) {
            val listOfPhotoGroups = response.body()?.let { photoGroupResponse ->
                photoGroupResponse.groups.listOfGroups.map { it.toPhotoGroup() }
            } ?: emptyList()

            if (listOfPhotoGroups.isEmpty()) {
                return getEmptyPage()
            }
            val nextKey = if (pageSize > listOfPhotoGroups.size) null else page + 1
            val prevKey = if (page == 1) null else page + 1
            return LoadResult.Page(listOfPhotoGroups, prevKey, nextKey)
        } else {
            return LoadResult.Error(HttpException(response))
        }
    }
}