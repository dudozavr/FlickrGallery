package com.dudnyk.framework.flickrgallery.data.model.dto.public_feed

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dudnyk.framework.flickrgallery.domain.model.PublicFeedTag

@Entity(tableName = "public_feed_tag")
class PublicFeedTagEntity(
    @PrimaryKey
    val tagName: String,
    val isMustContainAllTags: Boolean
)

fun PublicFeedTagEntity.toPublicFeedTag() = PublicFeedTag(
    tagName = tagName,
    isMustContainAllTags = isMustContainAllTags
)