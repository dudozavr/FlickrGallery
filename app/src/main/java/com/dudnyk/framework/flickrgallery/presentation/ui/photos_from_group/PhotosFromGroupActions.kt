package com.dudnyk.framework.flickrgallery.presentation.ui.photos_from_group

import com.dudnyk.framework.flickrgallery.domain.model.Photo

interface PhotosFromGroupActions {

    fun onPhotoClick(photo: Photo)
}