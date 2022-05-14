package com.dudnyk.framework.flickrgallery.data.model.dto.group

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PhotoGroupResponseDto(
    val groups: PhotoGroupsDto,
    val stat: String
)