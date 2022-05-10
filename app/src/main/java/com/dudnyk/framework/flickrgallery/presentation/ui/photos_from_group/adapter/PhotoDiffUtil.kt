package com.dudnyk.framework.flickrgallery.presentation.ui.photos_from_group.adapter

import androidx.recyclerview.widget.DiffUtil
import com.dudnyk.framework.flickrgallery.domain.model.Photo

class PhotoDiffUtil : DiffUtil.ItemCallback<Photo>() {

    override fun areItemsTheSame(oldItem: Photo, newItem: Photo) =
        oldItem.title == newItem.title

    override fun areContentsTheSame(oldItem: Photo, newItem: Photo) =
        oldItem == newItem
}