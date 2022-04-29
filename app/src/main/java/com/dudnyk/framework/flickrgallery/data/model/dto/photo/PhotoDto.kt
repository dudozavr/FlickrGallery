package com.dudnyk.framework.flickrgallery.data.model.dto.photo

import com.dudnyk.framework.flickrgallery.domain.model.Photo
import com.google.gson.annotations.SerializedName

data class PhotoDto(
    @SerializedName("dateadded")
    val dateAdded: String,
    val farm: Int,
    @SerializedName("height_h")
    val photoHeight: Int,
    val id: String,
    @SerializedName("isfamily")
    val isFamily: Int,
    @SerializedName("isfriend")
    val isFriend: Int,
    @SerializedName("ispublic")
    val isPublic: Int,
    val owner: String,
    @SerializedName("ownername")
    val ownerName: String,
    val secret: String,
    val server: String,
    val title: String,
    @SerializedName("url_h")
    val photoUrl: String,
    @SerializedName("width_h")
    val photoWidth: Int
)

fun PhotoDto.toPhoto() = Photo(
    id = id,
    title = title,
    photoUrl = photoUrl
)