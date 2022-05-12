package com.dudnyk.framework.flickrgallery.presentation.ui.photos_from_group.adapter

import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridItemDecoration(private val columnCount: Int) : RecyclerView.ItemDecoration() {

    companion object {
        private const val OFF_SETS_VALUE = 4f
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val column = position % columnCount
        val outRectValueToSet = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            OFF_SETS_VALUE,
            view.context.resources.displayMetrics
        ).toInt()

        outRect.bottom = outRectValueToSet
        if ((column + 1) != columnCount) {
            outRect.right = outRectValueToSet
        }
    }
}