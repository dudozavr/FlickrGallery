package com.dudnyk.framework.flickrgallery.common.utils

import com.dudnyk.framework.flickrgallery.common.Constants.DEFAULT_PUBLIC_FEED_TAG_NAME
import com.dudnyk.framework.flickrgallery.domain.model.PublicFeedTag

object PublicFeedUtils {

    fun getPublicFeedTag(publicFeedTag: PublicFeedTag?) =
        publicFeedTag ?: PublicFeedTag(
            tagName = DEFAULT_PUBLIC_FEED_TAG_NAME,
            isDefaultTag = true
        )
}