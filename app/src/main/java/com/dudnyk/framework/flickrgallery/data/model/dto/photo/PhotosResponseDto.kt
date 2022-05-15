package com.dudnyk.framework.flickrgallery.data.model.dto.photo

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PhotosResponseDto(
    val photos: PhotosDto = PhotosDto(
        page = 0,
        pages = 0,
        perPage = 0,
        listOfPhotos = emptyList(),
        total = 0
    ),
    val stat: String
)