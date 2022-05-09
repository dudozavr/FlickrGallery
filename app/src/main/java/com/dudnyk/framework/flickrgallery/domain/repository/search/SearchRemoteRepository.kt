package com.dudnyk.framework.flickrgallery.domain.repository.search

import androidx.paging.PagingData
import com.dudnyk.framework.flickrgallery.domain.model.PhotoGroup
import com.dudnyk.framework.flickrgallery.domain.model.Photo
import kotlinx.coroutines.flow.Flow

interface SearchRemoteRepository {

    fun getGroupsByQueryKey(queryKey: String): Flow<PagingData<PhotoGroup>>

    fun getPhotosByGroupId(groupId: String): Flow<PagingData<Photo>>
}