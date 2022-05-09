package com.dudnyk.framework.flickrgallery.presentation.ui.home.adapter.diff_util

import androidx.recyclerview.widget.DiffUtil
import com.dudnyk.framework.flickrgallery.domain.model.Photo

class PublicFeedPhotoDiffUtil(
    private val oldList: List<Photo>,
    private val newList: List<Photo>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].photoUrl == newList[newItemPosition].photoUrl

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]
}