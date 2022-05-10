package com.dudnyk.framework.flickrgallery.presentation.ui.search

import com.dudnyk.framework.flickrgallery.domain.model.PhotoGroup

interface GroupItemActions {
    fun onItemClick(photoGroup: PhotoGroup)
}