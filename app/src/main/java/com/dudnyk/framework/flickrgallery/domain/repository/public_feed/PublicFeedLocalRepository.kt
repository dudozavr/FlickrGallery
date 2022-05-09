package com.dudnyk.framework.flickrgallery.domain.repository.public_feed

import com.dudnyk.framework.flickrgallery.domain.model.PublicFeedTag

interface PublicFeedLocalRepository {

    suspend fun getPublicFeedTags(): List<PublicFeedTag>

    suspend fun insertPublicFeedTag(publicFeedTag: PublicFeedTag)

    suspend fun deletePublicFeedTag(publicFeedTag: PublicFeedTag)

    suspend fun deleteOldAndInsertNewPublicFeedTag(
        oldPublicFeedTag: PublicFeedTag,
        newPublicFeedTag: PublicFeedTag
    )
}