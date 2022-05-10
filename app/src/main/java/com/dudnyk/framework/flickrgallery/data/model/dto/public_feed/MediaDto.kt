package com.dudnyk.framework.flickrgallery.data.model.dto.public_feed

import com.google.gson.annotations.SerializedName

data class MediaDto(
    @SerializedName("m")
    val photoUrl: String
)