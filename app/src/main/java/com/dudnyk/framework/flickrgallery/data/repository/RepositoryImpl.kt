package com.dudnyk.framework.flickrgallery.data.repository

import com.dudnyk.framework.flickrgallery.BuildConfig
import com.dudnyk.framework.flickrgallery.common.Constants.GROUP_SEARCH_METHOD
import com.dudnyk.framework.flickrgallery.common.Constants.HTTP_RESPONSE_FORMAT
import com.dudnyk.framework.flickrgallery.common.Constants.OBTAINING_PHOTO_FROM_GROUP_METHOD
import com.dudnyk.framework.flickrgallery.common.Constants.PHOTO_EXTRAS
import com.dudnyk.framework.flickrgallery.data.model.dto.group.toGroup
import com.dudnyk.framework.flickrgallery.data.model.dto.photo.toPhoto
import com.dudnyk.framework.flickrgallery.data.remote.FlickrApi
import com.dudnyk.framework.flickrgallery.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val api: FlickrApi
) : Repository {

    override suspend fun getGroupsByQueryKey(queryKey: String) = api.getGroupsByQueryKey(
        method = GROUP_SEARCH_METHOD,
        apiKey = BuildConfig.API_KEY,
        queryKeyText = queryKey,
        format = HTTP_RESPONSE_FORMAT
    ).groups.listOfGroups.map { it.toGroup() }

    override suspend fun getPhotosByGroupId(groupId: String) = api.getPhotosByGroupId(
        method = OBTAINING_PHOTO_FROM_GROUP_METHOD,
        apiKey = BuildConfig.API_KEY,
        groupId = groupId,
        extras = PHOTO_EXTRAS,
        format = HTTP_RESPONSE_FORMAT
    ).photos.listOfPhotos.map { it.toPhoto() }
}