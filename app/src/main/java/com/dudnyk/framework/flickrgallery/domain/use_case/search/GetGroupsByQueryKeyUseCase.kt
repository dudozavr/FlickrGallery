package com.dudnyk.framework.flickrgallery.domain.use_case.search

import androidx.paging.PagingData
import com.dudnyk.framework.flickrgallery.domain.model.PhotoGroup
import com.dudnyk.framework.flickrgallery.domain.repository.search.SearchRemoteRepository
import kotlinx.coroutines.flow.Flow

class GetGroupsByQueryKeyUseCase(
    private val repository: SearchRemoteRepository
) {

    operator fun invoke(queryKey: String): Flow<PagingData<PhotoGroup>> =
        repository.getGroupsByQueryKey(queryKey)
}