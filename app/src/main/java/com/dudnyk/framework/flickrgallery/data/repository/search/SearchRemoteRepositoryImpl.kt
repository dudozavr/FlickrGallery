package com.dudnyk.framework.flickrgallery.data.repository.search

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.dudnyk.framework.flickrgallery.common.Constants.DEFAULT_PAGE_SIZE
import com.dudnyk.framework.flickrgallery.data.remote.FlickrApi
import com.dudnyk.framework.flickrgallery.domain.model.Photo
import com.dudnyk.framework.flickrgallery.domain.repository.search.SearchRemoteRepository
import kotlinx.coroutines.flow.Flow

class SearchRemoteRepositoryImpl(
    private val api: FlickrApi
) : SearchRemoteRepository {

    override fun getGroupsByQueryKey(queryKey: String) =
        getPagerWithConfig { PhotoGroupPagingSource(api = api, queryKey = queryKey) }

    override fun getPhotosByGroupId(groupId: String): Flow<PagingData<Photo>> =
        getPagerWithConfig { PhotoPagingSource(api = api, photoGroupId = groupId) }

    private fun <T : Any> getPagerWithConfig(pagingSourceFactory: () -> PagingSource<Int, T>) =
        Pager(
            PagingConfig(
                pageSize = DEFAULT_PAGE_SIZE,
                enablePlaceholders = false
            ), pagingSourceFactory = pagingSourceFactory
        ).flow
}