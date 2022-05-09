package com.dudnyk.framework.flickrgallery.presentation.ui.home.adapter.item_decoration

import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class HorizontalItemDecoration() : RecyclerView.ItemDecoration() {

    companion object {
        private const val OFF_SETS_VALUE = 8f
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val itemCount = parent.adapter?.itemCount ?: 0
        val position = parent.getChildAdapterPosition(view)
        val outRectValueToSet = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            OFF_SETS_VALUE,
            view.context.resources.displayMetrics
        ).toInt()

        if (itemCount -1 == position) {
            outRect.set(0, 0, 0, 0)
        } else {
            outRect.set(0, 0, outRectValueToSet, 0)
        }
    }
}