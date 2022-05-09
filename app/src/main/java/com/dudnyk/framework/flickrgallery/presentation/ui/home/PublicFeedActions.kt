package com.dudnyk.framework.flickrgallery.presentation.ui.home

import com.dudnyk.framework.flickrgallery.domain.model.Photo
import com.dudnyk.framework.flickrgallery.domain.model.PublicFeedTag

interface PublicFeedActions {

    fun onPublicFeedEdit(publicFeedTag: PublicFeedTag)

    fun onPublicFeedDelete(publicFeedTag: PublicFeedTag)

    fun onPublicFeedTryAgain(publicFeedTag: PublicFeedTag)

    fun onPhotoClick(photo: Photo)
}