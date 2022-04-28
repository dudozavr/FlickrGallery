package com.dudnyk.framework.flickrgallery.data.model.dto.group

import com.google.gson.annotations.SerializedName

data class GroupsDto(
    @SerializedName("group")
    val listOfGroups: List<GroupDto>,
    val page: Int,
    val pages: Int,
    @SerializedName("perpage")
    val perPage: Int,
    val total: String
)