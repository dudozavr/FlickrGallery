package com.dudnyk.framework.flickrgallery.presentation.ui.home.adapter.diff_util

import androidx.recyclerview.widget.DiffUtil
import com.dudnyk.framework.flickrgallery.common.PublicFeedState

class PublicFeedDiffUtil(
    private val oldList: List<PublicFeedState>,
    private val newList: List<PublicFeedState>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]
}
