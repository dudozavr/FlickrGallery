package com.dudnyk.framework.flickrgallery.data.model.dto.photo

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PhotosResponseDto(
    val photos: PhotosDto,
    val stat: String
)