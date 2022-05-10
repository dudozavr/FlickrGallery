package com.dudnyk.framework.flickrgallery.common.utils

import com.dudnyk.framework.flickrgallery.common.Constants.DEFAULT_PHOTO_GROUP_ICON_URL
import com.dudnyk.framework.flickrgallery.data.model.dto.group.PhotoGroupDto

object PhotoGroupUtils {

    fun getPhotoGroupIconUri(photoGroupDto: PhotoGroupDto) = photoGroupDto.run {
        if ((iconServer.toIntOrNull() ?: 0) > 0) {
            "https://farm${iconFarm}.staticflickr.com/${iconServer}/buddyicons/${id}.jpg"
        } else {
            DEFAULT_PHOTO_GROUP_ICON_URL
        }
    }
}