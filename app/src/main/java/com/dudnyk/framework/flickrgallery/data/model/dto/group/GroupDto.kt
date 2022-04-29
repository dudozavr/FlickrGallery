package com.dudnyk.framework.flickrgallery.data.model.dto.group

import com.dudnyk.framework.flickrgallery.domain.model.Group
import com.google.gson.annotations.SerializedName

data class GroupDto(
    @SerializedName("eighteenplus")
    val eighteenPlus: Int,
    @SerializedName("iconfarm")
    val iconFarm: Int,
    @SerializedName("iconserver")
    val iconServer: String,
    val members: String,
    val name: String,
    @SerializedName("nsid")
    val id: String,
    @SerializedName("pool_count")
    val poolCount: String,
    val privacy: String,
    @SerializedName("topic_count")
    val topicCount: String
)

fun GroupDto.toGroup() = Group(
    id = id,
    name = name
)