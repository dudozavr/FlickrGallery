package com.dudnyk.framework.flickrgallery.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PhotoGroup(
    val id: String,
    val name: String,
    val groupIconUrl: String
) : Parcelable {

    override fun toString() = name
}