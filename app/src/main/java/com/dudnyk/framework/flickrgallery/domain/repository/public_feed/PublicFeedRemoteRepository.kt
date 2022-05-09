package com.dudnyk.framework.flickrgallery.domain.repository.public_feed

import com.dudnyk.framework.flickrgallery.domain.model.PublicFeed
import com.dudnyk.framework.flickrgallery.domain.model.PublicFeedTag

interface PublicFeedRemoteRepository {

    suspend fun getPhotosFromPublicFeedByTag(publicFeedTag: PublicFeedTag): PublicFeed
}