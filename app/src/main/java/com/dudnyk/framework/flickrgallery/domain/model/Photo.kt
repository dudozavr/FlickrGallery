package com.dudnyk.framework.flickrgallery.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Photo(
    val title: String,
    val photoUrl: String?
) : Parcelable {

    override fun toString() = title
}