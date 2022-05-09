package com.dudnyk.framework.flickrgallery.presentation.ui.search.adapter

import androidx.recyclerview.widget.DiffUtil
import com.dudnyk.framework.flickrgallery.domain.model.PhotoGroup

class PhotoGroupDiffUtil : DiffUtil.ItemCallback<PhotoGroup>() {

    override fun areItemsTheSame(oldItem: PhotoGroup, newItem: PhotoGroup) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: PhotoGroup, newItem: PhotoGroup) =
        oldItem == newItem
}