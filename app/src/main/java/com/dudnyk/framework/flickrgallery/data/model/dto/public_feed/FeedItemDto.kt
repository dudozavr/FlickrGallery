package com.dudnyk.framework.flickrgallery.data.model.dto.public_feed

import com.dudnyk.framework.flickrgallery.domain.model.Photo
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FeedItemDto(
    val author: String,
    @Json(name = "author_id")
    val authorId: String,
    @Json(name = "date_taken")
    val dateTaken: String,
    val description: String,
    val link: String,
    val media: MediaDto,
    val published: String,
    val tags: String,
    val title: String
)

fun FeedItemDto.toPhoto() = Photo(
    title = title,
    largePhotoUrl = media.photoUrl,
    smallPhotoUrl = media.photoUrl
)