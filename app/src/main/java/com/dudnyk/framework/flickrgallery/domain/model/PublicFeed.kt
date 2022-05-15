package com.dudnyk.framework.flickrgallery.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PublicFeed(
    val photos: List<Photo>
) : Parcelable
