package com.dudnyk.framework.flickrgallery.data.model.dto.public_feed

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MediaDto(
    @Json(name = "m")
    val photoUrl: String
)