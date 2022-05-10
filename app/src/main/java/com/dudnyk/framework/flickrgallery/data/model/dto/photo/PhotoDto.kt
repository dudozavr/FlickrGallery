package com.dudnyk.framework.flickrgallery.data.model.dto.photo

import com.dudnyk.framework.flickrgallery.domain.model.Photo
import com.google.gson.annotations.SerializedName

data class PhotoDto(
    @SerializedName("dateadded")
    val dateAdded: String,
    val farm: Int,
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
    val largePhotoUrl: String?,
    @SerializedName("height_h")
    val largePhotoHeight: Int,
    @SerializedName("width_h")
    val largePhotoWidth: Int,
    @SerializedName("url_n")
    val smallPhotoUrl: String?,
    @SerializedName("height_n")
    val smallPhotoHeight: Int,
    @SerializedName("width_n")
    val smallPhotoWidth: Int
)

fun PhotoDto.toPhoto() = Photo(
    title = title,
    largePhotoUrl = largePhotoUrl ?: "",
    smallPhotoUrl = smallPhotoUrl ?: ""
)