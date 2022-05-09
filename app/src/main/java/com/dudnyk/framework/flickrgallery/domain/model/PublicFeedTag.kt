package com.dudnyk.framework.flickrgallery.domain.model

import android.os.Parcelable
import com.dudnyk.framework.flickrgallery.data.model.dto.public_feed.PublicFeedTagEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class PublicFeedTag(
    val tagName: String,
    val isMustContainAllTags: Boolean = false,
    val isDefaultTag: Boolean = false
) : Parcelable, Comparable<PublicFeedTag> {

    override fun compareTo(other: PublicFeedTag) = when {
        this.isDefaultTag -> 1
        other.isDefaultTag -> -1
        this.tagName.uppercase() < other.tagName.uppercase() -> -1
        this.tagName.uppercase() > other.tagName.uppercase() -> 1
        else -> 0
    }
}

fun PublicFeedTag.toPublicFeedTagEntity() = PublicFeedTagEntity(
    tagName = tagName,
    isMustContainAllTags = isMustContainAllTags
)