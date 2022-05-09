package com.dudnyk.framework.flickrgallery.data.repository.public_feed

import com.dudnyk.framework.flickrgallery.common.Constants.DEFAULT_PUBLIC_FEED_TAG_NAME
import com.dudnyk.framework.flickrgallery.common.Constants.TAG_FILTER_ALL
import com.dudnyk.framework.flickrgallery.common.Constants.TAG_FILTER_ANY
import com.dudnyk.framework.flickrgallery.data.model.dto.public_feed.toPublicFeed
import com.dudnyk.framework.flickrgallery.data.remote.FlickrApi
import com.dudnyk.framework.flickrgallery.domain.model.PublicFeed
import com.dudnyk.framework.flickrgallery.domain.model.PublicFeedTag
import com.dudnyk.framework.flickrgallery.domain.repository.public_feed.PublicFeedRemoteRepository
import javax.inject.Inject

class PublicFeedRemoteRepositoryImpl @Inject constructor(
    private val api: FlickrApi
) : PublicFeedRemoteRepository {

    override suspend fun getPhotosFromPublicFeedByTag(publicFeedTag: PublicFeedTag): PublicFeed =
        api.getPhotosFromPublicFeed(
            tags = publicFeedTag.tagName, tagMode = when (publicFeedTag.isMustContainAllTags) {
                true -> TAG_FILTER_ALL
                false -> TAG_FILTER_ANY
            }
        ).toPublicFeed(publicFeedTag = publicFeedTag)
}