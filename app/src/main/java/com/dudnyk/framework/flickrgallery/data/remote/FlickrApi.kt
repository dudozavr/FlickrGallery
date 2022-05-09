package com.dudnyk.framework.flickrgallery.data.remote

import androidx.annotation.IntRange
import com.dudnyk.framework.flickrgallery.BuildConfig
import com.dudnyk.framework.flickrgallery.common.Constants.DEFAULT_PAGE_SIZE
import com.dudnyk.framework.flickrgallery.common.Constants.GROUP_SEARCH_METHOD
import com.dudnyk.framework.flickrgallery.common.Constants.HTTP_RESPONSE_FORMAT
import com.dudnyk.framework.flickrgallery.common.Constants.MAX_PAGE_SIZE
import com.dudnyk.framework.flickrgallery.common.Constants.NO_JSON_CALL_BACK_VALUE
import com.dudnyk.framework.flickrgallery.common.Constants.OBTAINING_PHOTO_FROM_GROUP_METHOD
import com.dudnyk.framework.flickrgallery.common.Constants.PHOTO_EXTRAS
import com.dudnyk.framework.flickrgallery.data.model.dto.group.PhotoGroupResponseDto
import com.dudnyk.framework.flickrgallery.data.model.dto.photo.PhotosResponseDto
import com.dudnyk.framework.flickrgallery.data.model.dto.public_feed.PublicFeedResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrApi {

    @GET("/services/feeds/photos_public.gne")
    suspend fun getPhotosFromPublicFeed(
        @Query("format") format: String = HTTP_RESPONSE_FORMAT,
        @Query("nojsoncallback") noJSONCallBack: Int = NO_JSON_CALL_BACK_VALUE,
        @Query("tagmode") tagMode: String? = null,
        @Query("tags") tags: String? = null
    ): PublicFeedResponseDto

    @GET("/services/rest/")
    suspend fun getGroupsByQueryKey(
        @Query("text") queryKeyText: String,
        @Query("page") @IntRange(from = 1) page: Int = 1,
        @Query("per_page") @IntRange(
            from = 1,
            to = MAX_PAGE_SIZE.toLong()
        ) pageSize: Int = DEFAULT_PAGE_SIZE,
        @Query("method") method: String = GROUP_SEARCH_METHOD,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("format") format: String = HTTP_RESPONSE_FORMAT,
        @Query("nojsoncallback") noJSONCallBack: Int = NO_JSON_CALL_BACK_VALUE
    ): Response<PhotoGroupResponseDto>

    @GET("/services/rest/")
    suspend fun getPhotosByGroupId(
        @Query("group_id") groupId: String,
        @Query("page") @IntRange(from = 1) page: Int = 1,
        @Query("per_page") @IntRange(
            from = 1,
            to = MAX_PAGE_SIZE.toLong()
        ) pageSize: Int = DEFAULT_PAGE_SIZE,
        @Query("method") method: String = OBTAINING_PHOTO_FROM_GROUP_METHOD,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("extras") extras: String = PHOTO_EXTRAS,
        @Query("format") format: String = HTTP_RESPONSE_FORMAT,
        @Query("nojsoncallback") noJSONCallBack: Int = NO_JSON_CALL_BACK_VALUE
    ): Response<PhotosResponseDto>
}