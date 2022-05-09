package com.dudnyk.framework.flickrgallery.domain.use_case.search

import androidx.paging.PagingData
import com.dudnyk.framework.flickrgallery.domain.model.Photo
import com.dudnyk.framework.flickrgallery.domain.repository.search.SearchRemoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPhotosByGroupIdUseCase @Inject constructor(
    private val repository: SearchRemoteRepository
) {

    operator fun invoke(groupId: String): Flow<PagingData<Photo>> =
        repository.getPhotosByGroupId(groupId)
}