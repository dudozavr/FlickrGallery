package com.dudnyk.framework.flickrgallery.data.model.dto.group

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PhotoGroupsDto(
    @Json(name = "group")
    val listOfGroups: List<PhotoGroupDto>,
    val page: Int,
    val pages: Int,
    @Json(name = "perpage")
    val perPage: Int,
    val total: String
)