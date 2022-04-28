package com.dudnyk.framework.flickrgallery.data.model.dto.photo

import com.google.gson.annotations.SerializedName

data class PhotosDto(
    val page: Int,
    val pages: Int,
    @SerializedName("perpage")
    val perPage: Int,
    val photo: List<PhotoDto>,
    val total: Int
)