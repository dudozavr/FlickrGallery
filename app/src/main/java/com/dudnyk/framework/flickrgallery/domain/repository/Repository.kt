package com.dudnyk.framework.flickrgallery.domain.repository

import com.dudnyk.framework.flickrgallery.domain.model.Group
import com.dudnyk.framework.flickrgallery.domain.model.Photo

interface Repository {

    suspend fun getGroupsByQueryKey(queryKey: String): List<Group>

    suspend fun getPhotosByGroupId(groupId: String): List<Photo>
}