package com.dudnyk.framework.flickrgallery.domain.use_case.public_feed.local

import com.dudnyk.framework.flickrgallery.domain.repository.public_feed.PublicFeedLocalRepository

class GetPublicFeedTagsFromDBUseCase(
    private val repository: PublicFeedLocalRepository
) {
    suspend operator fun invoke() = repository.getPublicFeedTags()
}