package com.dudnyk.framework.flickrgallery.domain.use_case.public_feed.local

import com.dudnyk.framework.flickrgallery.common.PublicFeedResult
import com.dudnyk.framework.flickrgallery.common.sealed.*
import com.dudnyk.framework.flickrgallery.domain.model.PublicFeedTag
import com.dudnyk.framework.flickrgallery.domain.repository.public_feed.PublicFeedLocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class EditPublicFeedTegUseCase @Inject constructor(
    private val repository: PublicFeedLocalRepository
) {
    operator fun invoke(
        oldPublicFeedTag: PublicFeedTag,
        newPublicFeedTag: PublicFeedTag
    ): Flow<PublicFeedResult> =
        flow {
            try {
                emit(LoadingResult(id = oldPublicFeedTag))
                repository.deleteOldAndInsertNewPublicFeedTag(oldPublicFeedTag, newPublicFeedTag)
                emit(EditResult(id = newPublicFeedTag, oldId = oldPublicFeedTag))
            } catch (e: Exception) {
                emit(ErrorResult(id = oldPublicFeedTag, message = e.localizedMessage ?: ""))
            }
        }
}