package com.dudnyk.framework.flickrgallery.data.remote

import com.dudnyk.framework.flickrgallery.data.model.dto.group.GroupResponseDto
import com.dudnyk.framework.flickrgallery.data.model.dto.photo.PhotosResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrApi {

    @GET("/services/rest/")
    suspend fun getGroupsByQueryKey(
        @Query("method") method: String,
        @Query("api_key") apiKey: String,
        @Query("text") queryKeyText: String,
        @Query("format") format: String
    ): GroupResponseDto

    @GET("/services/rest/")
    suspend fun getPhotosByGroupId(
        @Query("method") method: String,
        @Query("api_key") apiKey: String,
        @Query("group_id") groupId: String,
        @Query("extras") extras: String,
        @Query("format") format: String
    ): PhotosResponseDto
}