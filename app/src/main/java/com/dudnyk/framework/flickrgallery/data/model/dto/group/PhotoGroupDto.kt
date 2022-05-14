package com.dudnyk.framework.flickrgallery.data.model.dto.group

import com.dudnyk.framework.flickrgallery.common.utils.PhotoGroupUtils.getPhotoGroupIconUri
import com.dudnyk.framework.flickrgallery.domain.model.PhotoGroup
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PhotoGroupDto(
    @Json(name = "eighteenplus")
    val eighteenPlus: Int,
    @Json(name = "iconfarm")
    val iconFarm: Int,
    @Json(name = "iconserver")
    val iconServer: String,
    val members: String,
    val name: String,
    @Json(name = "nsid")
    val id: String,
    @Json(name = "pool_count")
    val poolCount: String,
    val privacy: String,
    @Json(name = "topic_count")
    val topicCount: String
)

fun PhotoGroupDto.toPhotoGroup() = PhotoGroup(
    id = id,
    name = name,
    groupIconUrl = getPhotoGroupIconUri(this)
)