package com.dudnyk.framework.flickrgallery.domain.use_case.public_feed.local

import com.dudnyk.framework.flickrgallery.domain.model.PublicFeedTag
import com.dudnyk.framework.flickrgallery.domain.repository.public_feed.PublicFeedLocalRepository
import javax.inject.Inject

class InsertPublicFeedTagToBDUseCase @Inject constructor(
    private val repository: PublicFeedLocalRepository
) {
    suspend operator fun invoke(publicFeedTag: PublicFeedTag) {
        repository.insertPublicFeedTag(publicFeedTag)
    }
}