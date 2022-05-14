package com.dudnyk.framework.flickrgallery.data.model.dto.photo

import com.dudnyk.framework.flickrgallery.domain.model.Photo
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PhotoDto(
    @Json(name = "dateadded")
    val dateAdded: String,
    val farm: Int,
    val id: String,
    @Json(name = "isfamily")
    val isFamily: Int,
    @Json(name = "isfriend")
    val isFriend: Int,
    @Json(name = "ispublic")
    val isPublic: Int,
    val owner: String,
    @Json(name = "ownername")
    val ownerName: String,
    val secret: String,
    val server: String,
    val title: String,
    @Json(name = "url_h")
    val largePhotoUrl: String = "",
    @Json(name = "height_h")
    val largePhotoHeight: Int = 0,
    @Json(name = "width_h")
    val largePhotoWidth: Int = 0,
    @Json(name = "url_n")
    val smallPhotoUrl: String  = "",
    @Json(name = "height_n")
    val smallPhotoHeight: Int = 0,
    @Json(name = "width_n")
    val smallPhotoWidth: Int = 0
)

fun PhotoDto.toPhoto() = Photo(
    title = title,
    largePhotoUrl = largePhotoUrl,
    smallPhotoUrl = smallPhotoUrl
)