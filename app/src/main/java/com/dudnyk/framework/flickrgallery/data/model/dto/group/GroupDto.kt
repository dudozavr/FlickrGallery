package com.dudnyk.framework.flickrgallery.data.model.dto.group

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
    val nsId: String,
    @SerializedName("pool_count")
    val poolCount: String,
    val privacy: String,
    @SerializedName("topic_count")
    val topicCount: String
)