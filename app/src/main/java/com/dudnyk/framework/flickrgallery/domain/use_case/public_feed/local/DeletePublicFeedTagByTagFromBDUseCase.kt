package com.dudnyk.framework.flickrgallery.domain.use_case.public_feed.local

import com.dudnyk.framework.flickrgallery.common.PublicFeedResult
import com.dudnyk.framework.flickrgallery.common.sealed.DeleteResult
import com.dudnyk.framework.flickrgallery.common.sealed.ErrorResult
import com.dudnyk.framework.flickrgallery.common.sealed.LoadingResult
import com.dudnyk.framework.flickrgallery.domain.model.PublicFeedTag
import com.dudnyk.framework.flickrgallery.domain.repository.public_feed.PublicFeedLocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DeletePublicFeedTagByTagFromBDUseCase(
    private val repository: PublicFeedLocalRepository
) {
    operator fun invoke(publicFeedTag: PublicFeedTag): Flow<PublicFeedResult> =
        flow {
            try {
                emit(LoadingResult(id = publicFeedTag))
                repository.deletePublicFeedTag(publicFeedTag)
                emit(DeleteResult(id = publicFeedTag))
            } catch (e: Exception) {
                emit(ErrorResult(id = publicFeedTag, message = e.localizedMessage ?: ""))
            }
        }
}