package com.dudnyk.framework.flickrgallery.common.extension

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE

object ViewExtension {

    fun View.setVisibility(visible: Boolean) {
        visibility = if (visible) {
            VISIBLE
        } else {
            GONE
        }
    }
}