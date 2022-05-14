package com.dudnyk.framework.flickrgallery.data.model.dto.photo

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PhotosDto(
    val page: Int,
    val pages: Int,
    @Json(name = "perpage")
    val perPage: Int,
    @Json(name = "photo")
    val listOfPhotos: List<PhotoDto>,
    val total: Int
)