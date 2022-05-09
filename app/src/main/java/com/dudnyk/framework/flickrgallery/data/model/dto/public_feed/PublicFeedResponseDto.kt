package com.dudnyk.framework.flickrgallery.data.model.dto.public_feed

import com.dudnyk.framework.flickrgallery.domain.model.PublicFeed
import com.dudnyk.framework.flickrgallery.domain.model.PublicFeedTag

data class PublicFeedResponseDto(
    val description: String,
    val generator: String,
    val items: List<FeedItemDto>,
    val link: String,
    val modified: String,
    val title: String
)

fun PublicFeedResponseDto.toPublicFeed(publicFeedTag: PublicFeedTag) = PublicFeed(
    photos = items.map { it.toPhoto() }
)