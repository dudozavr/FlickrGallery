package com.dudnyk.framework.flickrgallery.domain.use_case.public_feed.remote

import com.dudnyk.framework.flickrgallery.common.PublicFeedResult
import com.dudnyk.framework.flickrgallery.common.sealed.ErrorResult
import com.dudnyk.framework.flickrgallery.common.sealed.LoadingResult
import com.dudnyk.framework.flickrgallery.common.sealed.SuccessResult
import com.dudnyk.framework.flickrgallery.common.utils.PublicFeedUtils.getPublicFeedTag
import com.dudnyk.framework.flickrgallery.domain.model.PublicFeedTag
import com.dudnyk.framework.flickrgallery.domain.repository.public_feed.PublicFeedRemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetPhotosFromPublicFeedByTagsUseCase(
    private val repository: PublicFeedRemoteRepository
) {
    operator fun invoke(publicFeedTag: PublicFeedTag? = null): Flow<PublicFeedResult> =
        flow {
            val publicFeedId = getPublicFeedTag(publicFeedTag)
            try {
                emit(LoadingResult(id = publicFeedId))
                val publicFeeds = repository.getPhotosFromPublicFeedByTag(publicFeedId)
                emit(SuccessResult(id = publicFeedId, data = publicFeeds))
            } catch (e: Exception) {
                emit(ErrorResult(id = publicFeedId, message = e.localizedMessage ?: ""))
            }
        }
}