package com.dudnyk.framework.flickrgallery.data.model.dto.public_feed

import com.dudnyk.framework.flickrgallery.domain.model.Photo
import com.google.gson.annotations.SerializedName

data class FeedItemDto(
    val author: String,
    @SerializedName("author_id")
    val authorId: String,
    @SerializedName("date_taken")
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
    photoUrl = media.photoUrl
)