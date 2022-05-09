package com.dudnyk.framework.flickrgallery.domain.use_case.search

import androidx.paging.PagingData
import com.dudnyk.framework.flickrgallery.domain.model.PhotoGroup
import com.dudnyk.framework.flickrgallery.domain.repository.search.SearchRemoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGroupsByQueryKeyUseCase @Inject constructor(
    private val repository: SearchRemoteRepository
) {

    operator fun invoke(queryKey: String): Flow<PagingData<PhotoGroup>> =
        repository.getGroupsByQueryKey(queryKey)
}